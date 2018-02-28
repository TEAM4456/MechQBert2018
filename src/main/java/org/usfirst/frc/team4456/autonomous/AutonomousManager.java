package org.usfirst.frc.team4456.autonomous;

import java.util.Map;
import java.util.HashMap;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class AutonomousManager {
	
	private ManagerMode mode;
	
	private WPI_TalonSRX[] talonArray;
	private Map<String, Integer> talonIndexMap;
	private Map<String, ControlMode> talonModeMap;
	private NetworkTableEntry[][] talonBufferArray;
	
	private int timeoutCounter;
	private int tick;
	private double tickInterval;
	private int bufferSize;
	
	private boolean clientIsReady;
	
	private Timer tickTimer;
	
	private NetworkTable autonomousData;
	private NetworkTable robotData;
	private NetworkTable bufferData;
	
	private NetworkTableEntry pingEntry;
	private NetworkTableEntry tickEntry;
	private NetworkTableEntry syncStopTickEntry;
	private NetworkTableEntry tickIntervalMsEntry;
	private NetworkTableEntry tickTimerEntry;
	private NetworkTableEntry bufferSizeEntry;
	private NetworkTableEntry talonModesEntry;
	private NetworkTableEntry managerModeEntry;
	private NetworkTableEntry robotReadyEntry;
	private NetworkTableEntry clientIsReadyEntry;
	private NetworkTableEntry recordingNameEntry;
	
	private enum ManagerMode { IDLE, RECORD_RUNNING, PLAYBACK_RUNNING }
	
	public AutonomousManager(int bufferSizeAdvance, double tickIntervalMs, WPI_TalonSRX[] talons) {
		
		talonArray = talons;
		talonIndexMap = new HashMap<>();
		for (int i = 0; i < talonArray.length; i++) {
			// using name->index map ensures that recording and playback works, not dependent on talon order
			talonIndexMap.put(talonArray[i].getName(), i);
		}
		
		timeoutCounter = 0;
		tick = 0;
		
		bufferSize = bufferSizeAdvance + 1;
		
		tickInterval = tickIntervalMs / 1000;
		
		tickTimer = new Timer();
		tickTimer.start();
		//tickTimer.reset(); // maybe unnecessary
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		autonomousData = inst.getTable("AutonomousData");
		robotData = autonomousData.getSubTable("RobotState");
		bufferData = autonomousData.getSubTable("BufferData");
		
		pingEntry = robotData.getEntry("ping");
		tickEntry = robotData.getEntry("tick");
		syncStopTickEntry = robotData.getEntry("syncStopTick");
		tickIntervalMsEntry = robotData.getEntry("tickIntervalMs");
		tickTimerEntry = robotData.getEntry("tickTimer");
		bufferSizeEntry = robotData.getEntry("bufferSize");
		talonModesEntry = robotData.getEntry("talonModes");
		managerModeEntry = robotData.getEntry("managerMode");
		robotReadyEntry = robotData.getEntry("robotReady");
		clientIsReadyEntry = robotData.getEntry("clientIsReady");
		recordingNameEntry = robotData.getEntry("recordingName");
		
		pingEntry.setBoolean(false); // false = robot->client ping, true = client->robot pong
		syncStopTickEntry.setDefaultNumber(-1);
		tickIntervalMsEntry.setDouble(tickIntervalMs);
		bufferSizeEntry.setNumber(bufferSize);
		robotReadyEntry.setBoolean(false);
		clientIsReadyEntry.setDefaultBoolean(false); // sets if doesn't exist
		recordingNameEntry.setDefaultString(""); // sets if doesn't exist
		
		setAndWriteManagerMode(ManagerMode.IDLE);
		
		talonModeMap = new HashMap<>();
		updateAndWriteTalonModes();
		
		talonBufferArray = new NetworkTableEntry[talonArray.length][];
		for (int i = 0; i < talonArray.length; i++) {
			talonBufferArray[i] = generateEntriesForTalonBuffer(talonArray[i]);
		}
		
		clientIsReady = false;
		
	}
	
	private void pingClient() {
		if (pingEntry.getBoolean(false)) {
			pingEntry.setBoolean(false);
			timeoutCounter = 0;
		} else {
			timeoutCounter++;
		}
	}
	
	private NetworkTableEntry[] generateEntriesForTalonBuffer(WPI_TalonSRX talon) {
		NetworkTableEntry[] entries = new NetworkTableEntry[bufferSize];
		for (int i = 0; i < bufferSize; i++) {
			NetworkTableEntry entry = bufferData.getEntry(talon.getName() + "-" + i);
			entry.setDouble(0);
			entries[i] = entry;
		}
		return entries;
	}
	
	private void writeToTalonBuffer(WPI_TalonSRX talon, double value) {
		talonBufferArray[talonIndexMap.get(talon.getName())][tick % bufferSize].setDouble(value); // oh jeez
	}
	
	private void updateAndWriteTalonModes() {
		String talonModes = "";
		for (WPI_TalonSRX talon : talonArray) {
			ControlMode controlMode;
			if (talon.getControlMode() == ControlMode.Velocity) {
				controlMode = ControlMode.Velocity;
			} else {
				controlMode = ControlMode.Current;
			}
			talonModeMap.put(talon.getName(), controlMode); // will overwrite value for key
			talonModes += talon.getName() + ":" + controlMode.toString() + "|";
		}
		talonModes = talonModes.substring(0, talonModes.length() - 1); // remove trailing delimiter
		talonModesEntry.setString(talonModes);
	}
	
	private void readAndUpdateTalonModes() {
		/* TODO: handle default of "" */
		String talonModes = talonModesEntry.getString("");
		if (!talonModes.equals("")) {
			for (String talonAndMode : talonModes.split("\\|")) {
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
		
		double tickTimerVal = tickTimer.get();
		tickTimerEntry.setDouble(tickTimerVal); // testing (maybe not?)
		
		if (tickTimerVal > tickInterval) {
			
			if (mode == ManagerMode.RECORD_RUNNING) {
				
				if (timeoutCounter >= bufferSize - 1) {
					stopRecording(true);
					throw new AutonomousManagerException("timeout exceeded buffer size while recording!");
				}
				
				if (clientIsReady) {
					
					if (!clientIsReadyEntry.getBoolean(false)) {
						stopRecording(true);
						throw new AutonomousManagerException("client became unready while recording!");
					}
					
					for (WPI_TalonSRX talon : talonArray) {
						if (talon.getControlMode() == ControlMode.Velocity) {
							writeToTalonBuffer(talon, talon.getSelectedSensorVelocity(0));
						} else {
							writeToTalonBuffer(talon, talon.getOutputCurrent());
						}
					}
					
					tick++;
				} else {
					clientIsReady = clientIsReadyEntry.getBoolean(false);
				}
				
				// update tick info
				tickEntry.setNumber(tick);
				
			} else if (mode == ManagerMode.PLAYBACK_RUNNING) {
				
				if (timeoutCounter >= bufferSize - 1) {
					stopPlayback();
					throw new AutonomousManagerException("timeout exceeded buffer size during playback!");
				}
				
				if (clientIsReady) {
				
					/* playback stuffs */
					
					tick++;
				} else {
					clientIsReady = clientIsReadyEntry.getBoolean(false);
				}
				
				// update tick info
				tickEntry.setNumber(tick);
				
			}
			
			tickTimer.reset(); // maybe not ideal solution
			tickTimer.start(); // maybe not ideal solution
			pingClient();
		}
		
	}
	
	public void startRecording(String recordingName) throws AutonomousManagerException {
		switch (mode) {
			case RECORD_RUNNING:
				throw new AutonomousManagerException("startRecording() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("startRecording() called while playback is running!");
			case IDLE:
				// recording setup and start stuff here
				tick = 0;
				tickEntry.setNumber(0);
				syncStopTickEntry.setNumber(-1); // no tick to stop at
				recordingNameEntry.setString(recordingName);
				updateAndWriteTalonModes();
				tickTimer.start();
				setAndWriteManagerMode(ManagerMode.RECORD_RUNNING);
				robotReadyEntry.setBoolean(true);
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
				setAndWriteManagerMode(ManagerMode.PLAYBACK_RUNNING);
				// playback setup and start stuff here
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
				// recording stop stuff here
				if (cancelRecording) { recordingNameEntry.setString("CLIENT::CANCEL_RECORDING"); }
				syncStopTickEntry.setNumber(tick); // tell client to stop at tick
				tickTimer.reset();
				tickTimer.stop(); // maybe unnecessary
				setAndWriteManagerMode(ManagerMode.IDLE);
				clientIsReady = false;
				robotReadyEntry.setBoolean(false);
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
				// playback stop stuff here
				setAndWriteManagerMode(ManagerMode.IDLE);
				break;
		}
	}
	
	public boolean isRecordRunning() {
		return (mode == ManagerMode.RECORD_RUNNING);
	}
	
	public boolean isPlaybackRunning() {
		return (mode == ManagerMode.PLAYBACK_RUNNING);
	}
	
}
