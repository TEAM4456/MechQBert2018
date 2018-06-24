package org.usfirst.frc.team4456.autonomous;

import java.util.Map;
import java.util.HashMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class AutonomousManager {
	
	/*
	The core of the recording/playback system. Now commented very verbosely.
	Intended to be used by AutonomousHandler, which has game-specific code, and tells this what to do.
	This implementation is extremely overcomplicated, and will be superseded by an improved system.
	It's still something to learn from, though, so here it is.
	
	If anything here isn't commented on, then it's self-explanatory enough to make a comment clunky and annoying.
	 */
	
	private ManagerMode mode; // what we're currently supposed to be doing (the mode, duh)
	
	private WPI_TalonSRX[] talonArray; // the talons to be used
	private Map<String, Integer> talonIndexMap; // map to track talons by their names
	private Map<String, ControlMode> talonModeMap; // map for talons' modes by name
	private NetworkTableEntry[][] talonBufferArray; // the talon buffers, explained below
	
	private int timeoutCounter; // used to abort recording/playback if connection is lost for too long
	private int tick; // what step of values the recording/playback is currently at
	private int bufferSize; // the size of each talon buffer
	
	private boolean playbackDataInitialized; // used to prevent playback from starting before data is received
	
	private Timer cycleTimer; // timer used for debugging, to ensure that cycles are accurate
	
	private int cycleCounter; // what step the cycle is currently at in the interval
	private int interval; // used to do things once per a certain amount of run() calls
	
	private NetworkTable autonomousData; // network table encapsulating everything
	private NetworkTable robotData; // sub-table for synchronizing between the robot and client
	private NetworkTable bufferData; // sub-table for all of the talon data
	
	/*
	An entry is a reference/handle to a variable in a table which NetworkTables synchronizes between everything
	connected to the same NetworkTables instance.
	 */
	private NetworkTableEntry enabledEntry; // whether or not the robot is enabled
	private NetworkTableEntry pingEntry; // used as a connection tracker, and a client pacemaker
	private NetworkTableEntry tickEntry; // what tick the robot (not client!) is currently at
	private NetworkTableEntry syncStopTickEntry; // what tick the robot and client should both stop at
	private NetworkTableEntry intervalEntry; /* robot->client: remember the interval for the recording
	                                            client->robot: use the interval of the recording */
	private NetworkTableEntry bufferSizeEntry; // robot->client: use the same buffer size as me
	private NetworkTableEntry talonModesEntry; /* robot->client: remember the talon modes for the recording
	                                              client->robot: use the talon modes of the recording */
	private NetworkTableEntry managerModeEntry; // the current mode, always dictated by the robot
	private NetworkTableEntry recordingNameEntry; // robot->client: save or fetch the recording by name
	private NetworkTableEntry cycleTimerEntry; // explained above
	
	private enum ManagerMode { IDLE, RECORD_RUNNING, PLAYBACK_RUNNING } // explained above
	
	public AutonomousManager(int bufferSizeAdvance, int cycleInterval, WPI_TalonSRX[] talons) {
		
		cycleCounter = 1; // cycles start at 1. why? it's more human-intuitive.
		interval = cycleInterval;
		
		timeoutCounter = 0;
		tick = 0;
		
		bufferSize = bufferSizeAdvance + 1; // 1 for current data, bufferSizeAdvance for advance data
		
		playbackDataInitialized = false;
		
		cycleTimer = new Timer(); // debugging, explained above
		cycleTimer.start(); // debugging, explained above
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault(); // get a NetworkTables instance
		autonomousData = inst.getTable("AutonomousData"); // get the main table
		robotData = autonomousData.getSubTable("RobotState"); // get the sub-table explained above
		bufferData = autonomousData.getSubTable("BufferData"); // get the sub-table explained above
		
		/* get all of the entries for robot/state data, explained above */
		enabledEntry = robotData.getEntry("enabled");
		pingEntry = robotData.getEntry("ping");
		tickEntry = robotData.getEntry("tick");
		syncStopTickEntry = robotData.getEntry("syncStopTick");
		intervalEntry = robotData.getEntry("interval");
		bufferSizeEntry = robotData.getEntry("bufferSize");
		talonModesEntry = robotData.getEntry("talonModes");
		managerModeEntry = robotData.getEntry("managerMode");
		recordingNameEntry = robotData.getEntry("recordingName");
		cycleTimerEntry = robotData.getEntry("cycleTimer"); // debugging, explained above
		
		pingEntry.setBoolean(false); // false = robot->client ping, true = client->robot pong
		syncStopTickEntry.setDefaultNumber(-1); // -1 = no tick to stop at yet
		intervalEntry.setDouble(interval);
		bufferSizeEntry.setNumber(bufferSize);
		recordingNameEntry.setDefaultString(""); // sets if doesn't exist (won't overwrite)
		
		setAndWriteManagerMode(ManagerMode.IDLE);
		
		talonArray = talons;
		talonIndexMap = new HashMap<>();
		for (int i = 0; i < talonArray.length; i++) {
			/* using name->index map ensures that recording and playback works, independent of talon order */
			talonIndexMap.put(talonArray[i].getName(), i);
		}
		
		talonModeMap = new HashMap<>();
		updateAndWriteTalonModes();
		
		/*
		Alright, now we get to the complicated part: the talon buffers.
		The talon buffers use a circular buffer system, by which the current index of the data is the current tick %
		the size of the buffer. This allows the data to advance continuously forwards using a reasonable number of
		entries, and not just dump a huge pile of data all at once. By keeping the buffer size and tick synchronized
		between the robot and client, the data stays synchronized.
		Each talon buffer is just an array of entries, and talonBufferArray is the array of these arrays.
		More explanation below at each component of the system.
		 */
		
		/* make the talon buffers, and populate the buffer array with them */
		talonBufferArray = new NetworkTableEntry[talonArray.length][]; // one buffer for each talon
		for (int i = 0; i < talonArray.length; i++) {
			talonBufferArray[i] = generateEntriesForTalonBuffer(talonArray[i]);
		}
		
	}
	
	private void pingClient() {
		if (pingEntry.getBoolean(false)) { // is there a client->robot ping? (panic default: "no")
			pingEntry.setBoolean(false); // make it a robot->client ping
			timeoutCounter = 0; // reset the timeout counter
		} else {
			timeoutCounter++;
		}
	}
	
	private NetworkTableEntry[] generateEntriesForTalonBuffer(WPI_TalonSRX talon) {
		NetworkTableEntry[] entries = new NetworkTableEntry[bufferSize]; // make the buffer (an array of entries)
		for (int i = 0; i < bufferSize; i++) {
			NetworkTableEntry entry = bufferData.getEntry(talon.getName() + "-" + i); /* fetch an entry by name
			                                                                                  and index in buffer */
			entry.setDouble(0); // set it to 0, to default it
			entries[i] = entry; // put it in the buffer
		}
		return entries;
	}
	
	private void flushTalonBuffer() {
		/* set every value in each buffer to 0 to flush unwanted, residual data */
		for (NetworkTableEntry[] entries : talonBufferArray) {
			for (NetworkTableEntry entry : entries) {
				entry.setDouble(0);
			}
		}
	}
	
	private void writeToTalonBuffer(WPI_TalonSRX talon, double value) {
		/*
		This is a complicated action that has been completely inlined. It's pretty scary to look at, so let's break it
		down. We're accessing talonBufferArray, which we need two indices for. The first index we need is which talon
		buffer to get from the array. The second index is what entry to pull from that buffer. We get the index of the
		desired talon buffer using the talonIndexMap. We give it the talon's name, and it returns the buffer's index.
		This is what talonIndexMap.get(talon.getName()) is doing. Next, we use the circular buffer system explained
		above to get the second index. Now that we have both indices, talonBufferArray gives us an entry, which we set
		to the value.
		 */
		talonBufferArray[talonIndexMap.get(talon.getName())][tick % bufferSize].setDouble(value);
	}
	
	private double readFromTalonBuffer(WPI_TalonSRX talon) {
		/*
		This uses the same process as explained above, except it's getting a value instead of setting a value.
		talonBufferArray gives us an entry, and we return its value. (panic default: 0)
		 */
		return talonBufferArray[talonIndexMap.get(talon.getName())][tick % bufferSize].getDouble(0);
	}
	
	private void updateAndWriteTalonModes() {
		/*
		The talons' modes are communicated using a single string. The format goes "Name:Mode|Name:Mode|Name:Mode" and
		is constructed accordingly here. If the talon is in Velocity or Position mode, then it's recorded in their
		respective mode. Otherwise, it's recorded in PercentOutput mode.
		
		!!! It's critical to note that for each recording, this is done exactly ONCE, instantly BEFORE the recording
		begins! If a talon changes modes after this, then it could be recorded using the wrong values! This was
		responsible for the death of a gearbox, when a talon started in PercentOutput mode before the recording, but
		then changed to Position mode, causing it to get a value of ~900 in PercentOutput mode during playback. !!!
		 */
		String talonModes = "";
		for (WPI_TalonSRX talon : talonArray) {
			ControlMode controlMode = talon.getControlMode();
			if (!(controlMode == ControlMode.Velocity || controlMode == ControlMode.Position)) {
				controlMode = ControlMode.PercentOutput;
			}
			talonModeMap.put(talon.getName(), controlMode); // update talonModeMap (will overwrite, no duplicates)
			talonModes += talon.getName() + ":" + controlMode.toString() + "|";
		}
		talonModes = talonModes.substring(0, talonModes.length() - 1); // remove trailing delimiter
		talonModesEntry.setString(talonModes);
	}
	
	private void readAndUpdateTalonModes() {
		/*
		This gets the formatted talon modes string (set by the client this time, from the recording), deconstructs it,
		and updates talonModeMap.
		 */
		String talonModes = talonModesEntry.getString("");
		if (!talonModes.equals("")) {
			talonModeMap.clear();
			for (String talonAndMode : talonModes.split("\\|")) { // double \ needed to escape special regex
				String[] talonAndModeArray = talonAndMode.split(":");
				talonModeMap.put(talonAndModeArray[0], ControlMode.valueOf(talonAndModeArray[1]));
			}
		}
	}
	
	private void setAndWriteManagerMode(ManagerMode newMode) {
		mode = newMode;
		managerModeEntry.setString(mode.toString());
	}
	
	public void run() throws AutonomousManagerException {
		
		if (cycleCounter >= interval) { // has it been enough run() calls yet? (should never be greater than, really)
			
			pingClient(); // ping the client first
			
			if (mode == ManagerMode.RECORD_RUNNING) { // are we supposed to be recording?
				
				/* abort the recording if there has been no client response for longer than the buffer can handle */
				if (timeoutCounter > bufferSize) {
					stopRecording(true);
					throw new AutonomousManagerException("timeout exceeded buffer size while recording!");
				}
				
				for (WPI_TalonSRX talon : talonArray) {
					/* if the talon is in Velocity or Position mode, record closed-loop target */
					ControlMode controlMode = talon.getControlMode();
					if (controlMode == ControlMode.Velocity || controlMode == ControlMode.Position) {
						writeToTalonBuffer(talon, talon.getClosedLoopTarget(0));
					} else { // otherwise, record current output percent
						writeToTalonBuffer(talon, talon.getMotorOutputPercent());
					}
				}
				
				/* advance the tick */
				tick++;
				tickEntry.setNumber(tick);
				
			} else if (mode == ManagerMode.PLAYBACK_RUNNING) { // are we supposed to be in playback?
				
				/* abort playback if there has been no client response for longer than the buffer can handle */
				if (timeoutCounter > bufferSize) {
					stopPlayback();
					throw new AutonomousManagerException("timeout exceeded buffer size during playback!");
				}
				
				if (playbackDataInitialized) { // are we ready to do playback yet?
					
					/* if we're at the stop point, stop playback. why subtract 2? that's just what got it working. */
					if (tick > syncStopTickEntry.getDouble(0) - 2) {
						stopPlayback();
					} else {
						tick++;
					}
					
				} else {
					playbackDataInitialized = true;
					interval = (int)intervalEntry.getDouble(-1); // get interval to use (panic default: -1)
					/* if no stop tick has been set, or if the talon modes string is empty, don't start playback */
					if (interval == -1 || talonModesEntry.getString("").equals("")) {
						playbackDataInitialized = false;
					}
					readAndUpdateTalonModes();
					/*
					Note: checking if the recording has talons which the manager doesn't was planned, but never
					implemented. If the recording has talons which the manager doesn't, then we don't know what happens
					yet.
					 */
				}
				
				/* update tick info, advanced earlier */
				tickEntry.setNumber(tick);
				
			} else { // we're in idle mode
				/* if there has been no client response for longer than the buffer can handle, warn the user */
				if (timeoutCounter > bufferSize && timeoutCounter % bufferSize - 1 == 0) {
					throw new AutonomousManagerException("timeout exceeded buffer size! (IDLE WARNING)");
				}
			}
			
			cycleCounter = 1; // reset the cycle
			cycleTimerEntry.setDouble(cycleTimer.get()); // debugging, explained above
			cycleTimer.reset(); // debugging, explained above
			cycleTimer.start(); // debugging, explained above
		} else {
			cycleCounter++;
		}
		
		/*
		What? This is strange. Why is this part detached from the other playback part? The answer: the talons will
		stutter if they're not updated on each run() call, regardless of whether or not there's new data yet. This will
		run on each run() call, whereas the other playback section won't.
		 */
		if (mode == ManagerMode.PLAYBACK_RUNNING) {
			for (WPI_TalonSRX talon : talonArray) {
				/* give the talon its value from its buffer, in its mode from talonModeMap */
				talon.set(talonModeMap.get(talon.getName()), readFromTalonBuffer(talon));
			}
		}
		
	}
	
	public void startRecording(String recordingName) throws AutonomousManagerException {
		switch (mode) {
			case RECORD_RUNNING:
				throw new AutonomousManagerException("startRecording() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("startRecording() called while playback is running!");
			case IDLE:
				/* set things up to start recording */
				tick = 0;
				tickEntry.setNumber(0);
				syncStopTickEntry.setNumber(-1); // no tick to stop at
				recordingNameEntry.setString(recordingName);
				flushTalonBuffer();
				updateAndWriteTalonModes();
				setAndWriteManagerMode(ManagerMode.RECORD_RUNNING);
				break;
		}
	}
	
	public void startPlayback(String recordingName) throws AutonomousManagerException {
		switch (mode) {
			case RECORD_RUNNING:
				throw new AutonomousManagerException("startPlayback() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("startPlayback() called while playback is running!");
			case IDLE:
				/* set things up to start playback */
				tick = 0;
				tickEntry.setNumber(0);
				recordingNameEntry.setString(recordingName);
				flushTalonBuffer();
				setAndWriteManagerMode(ManagerMode.PLAYBACK_RUNNING);
				playbackDataInitialized = false;
				break;
		}
	}
	
	public void stopRecording(boolean cancelRecording) throws AutonomousManagerException {
		switch (mode) {
			case IDLE:
				throw new AutonomousManagerException("stopRecording() called without starting recording!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("stopRecording() called while playback is running!");
			case RECORD_RUNNING:
				/* stop the recording */
				if (cancelRecording) {
					recordingNameEntry.setString("CLIENT::CANCEL_RECORDING"); // special string client will look for
				}
				syncStopTickEntry.setNumber(tick - 1); // tell client to stop at tick. why tick - 1? it's what works.
				setAndWriteManagerMode(ManagerMode.IDLE);
				break;
		}
	}
	
	public void stopPlayback() throws AutonomousManagerException {
		switch (mode) {
			case IDLE:
				throw new AutonomousManagerException("stopPlayback() called without starting playback!");
			case RECORD_RUNNING:
				throw new AutonomousManagerException("stopPlayback() called while recording is running!");
			case PLAYBACK_RUNNING:
				/* stop playback */
				syncStopTickEntry.setNumber(tick); // tell client to stop at tick
				setAndWriteManagerMode(ManagerMode.IDLE);
				break;
		}
	}
	
	public void updateEnabledStatus(boolean enabled) {
		enabledEntry.setBoolean(enabled);
	}
	
	public boolean isRecordRunning() {
		return (mode == ManagerMode.RECORD_RUNNING);
	}
	
	public boolean isPlaybackRunning() {
		return (mode == ManagerMode.PLAYBACK_RUNNING);
	}
	
}
