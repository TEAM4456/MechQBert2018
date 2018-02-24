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
	
	// Note: maybe improve exception messages about "x() called in y mode!"
	
	private ManagerMode mode;
	
	private WPI_TalonSRX[] talonArray;
	private Map<String, Integer> talonIndexMap;
	private Map<String, ControlMode> talonModeMap;
	private NetworkTableEntry[][] talonBufferArray;
	
	private int tick;
	private double tickInterval;
	private int bufferSize;
	
	private Timer tickTimer;
	
	private NetworkTable autonomousData;
	private NetworkTable robotData;
	private NetworkTable bufferData;
	
	private NetworkTableEntry tickEntry;
	private NetworkTableEntry tickIntervalMsEntry;
	private NetworkTableEntry tickTimerEntry;
	private NetworkTableEntry bufferSizeEntry;
	private NetworkTableEntry talonModesEntry;
	private NetworkTableEntry managerStateEntry; // string (maybe change to int)
	private NetworkTableEntry robotReadyEntry; // bool
	private NetworkTableEntry clientReadyEntry; // bool
	
	private enum ManagerMode { IDLE, RECORD_WAITING, PLAYBACK_WAITING, RECORD_RUNNING, PLAYBACK_RUNNING }
	
	public AutonomousManager(int bufferSizeAdvance, double tickIntervalMs, WPI_TalonSRX[] talons) {
		
		talonArray = talons;
		talonIndexMap = new HashMap<>();
		for (int i = 0; i < talonArray.length; i++) {
			// using name->index map ensures that recording and playback works, not dependent on talon order
			talonIndexMap.put(talonArray[i].getName(), i);
		}
		
		mode = ManagerMode.IDLE;
		
		tick = 0;
		bufferSize = bufferSizeAdvance + 1;
		
		tickInterval = tickIntervalMs / 1000;
		
		tickTimer = new Timer();
		tickTimer.start(); // move to start methods later
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		autonomousData = inst.getTable("AutonomousData");
		robotData = autonomousData.getSubTable("RobotState");
		bufferData = autonomousData.getSubTable("BufferData");
		
		tickEntry = robotData.getEntry("tick");
		tickIntervalMsEntry = robotData.getEntry("tickIntervalMs");
		tickTimerEntry = robotData.getEntry("tickTimer");
		bufferSizeEntry = robotData.getEntry("bufferSize");
		talonModesEntry = robotData.getEntry("talonModes");
		managerStateEntry = robotData.getEntry("managerState");
		robotReadyEntry = robotData.getEntry("robotReady");
		clientReadyEntry = robotData.getEntry("clientReady");
		
		tickIntervalMsEntry.setDouble(tickIntervalMs);
		bufferSizeEntry.setNumber(bufferSize);
		managerStateEntry.setString("IDLE");
		robotReadyEntry.setBoolean(false);
		clientReadyEntry.setDefaultBoolean(false);
		
		talonModeMap = new HashMap<>();
		updateAndWriteTalonModes();
		
		talonBufferArray = new NetworkTableEntry[talonArray.length][];
		for (int i = 0; i < talonArray.length; i++) {
			talonBufferArray[i] = generateEntriesForTalonBuffer(talonArray[i]);
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
		for (int i = 0; i < talonArray.length; i++) {
			talonModeMap.put(talonArray[i].getName(), talonArray[i].getControlMode()); // will overwrite value for key
			talonModes += talonArray[i].getName() + ":" + talonArray[i].getControlMode().toString() + "|";
		}
		talonModes = talonModes.substring(0, talonModes.length() - 1); // remove trailing delimiter
		talonModesEntry.setString(talonModes);
	}
	
	public void run() {
		
		double tickTimerVal = tickTimer.get();
		
		if (tickTimerVal > tickInterval) {
			
			for (WPI_TalonSRX talon : talonArray) {
				writeToTalonBuffer(talon, talon.getSelectedSensorVelocity(0));
			}
			
			updateAndWriteTalonModes();
			
			/*
			NOTE: change behavior based on talon control mode:
			  - closed-loop: velocity
			  - open-loop: voltage or percentoutput
			 */
			
			// update tick info
			tick++;
			tickEntry.setNumber(tick);
			tickTimerEntry.setDouble(tickTimerVal); // testing (maybe not?)
			tickTimer.reset(); // might not be ideal solution
			tickTimer.start(); // might not be ideal solution
		}
		
	}
	
	public void setRecordingMode(/*...*/) throws AutonomousManagerException {
		switch (mode) {
			case RECORD_RUNNING:
				throw new AutonomousManagerException("setRecordingMode() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("setRecordingMode() called while playback is running!");
			default:
				mode = ManagerMode.RECORD_WAITING;
				// recording setup stuff here
				break;
		}
	}
	
	public void setPlaybackMode(/*...*/) throws AutonomousManagerException {
		switch (mode) {
			case RECORD_RUNNING:
				throw new AutonomousManagerException("setPlaybackMode() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("setPlaybackMode() called while playback is running!");
			default:
				mode = ManagerMode.PLAYBACK_WAITING;
				// playback setup stuff here
				break;
		}
	}
	
	public void startRecording(/*...*/) throws AutonomousManagerException {
		switch (mode) {
			case IDLE:
				throw new AutonomousManagerException("startRecording() called without setting mode!");
			case RECORD_RUNNING:
				throw new AutonomousManagerException("startRecording() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("startRecording() called while playback is running!");
			case PLAYBACK_WAITING:
				throw new AutonomousManagerException("startRecording() called in playback mode!");
			case RECORD_WAITING:
				mode = ManagerMode.RECORD_RUNNING;
				// recording start stuff here
				break;
		}
	}
	
	public void startPlayback(/*...*/) throws AutonomousManagerException {
		switch (mode) {
			case IDLE:
				throw new AutonomousManagerException("startPlayback() called without setting mode!");
			case RECORD_RUNNING:
				throw new AutonomousManagerException("startPlayback() called while recording is running!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("startPlayback() called while playback is running!");
			case RECORD_WAITING:
				throw new AutonomousManagerException("startPlayback() called while in recording mode!");
			case PLAYBACK_WAITING:
				mode = ManagerMode.PLAYBACK_RUNNING;
				// playback start stuff here
				break;
		}
	}
	
	public void stopRecording(/*...*/) throws AutonomousManagerException {
		switch (mode) {
			case IDLE:
				throw new AutonomousManagerException("stopRecording() called without setting mode!");
			case PLAYBACK_RUNNING:
				throw new AutonomousManagerException("stopRecording() called while playback is running!");
			case RECORD_WAITING:
				throw new AutonomousManagerException("stopRecording() called without starting recording!");
			case PLAYBACK_WAITING:
				throw new AutonomousManagerException("stopRecording() called while in playback mode!");
			case RECORD_RUNNING:
				mode = ManagerMode.IDLE; // maybe change to ManagerMode.RECORD_WAITING?
				// recording stop stuff here
				break;
		}
	}
	
	public void stopPlayback() throws AutonomousManagerException {
		switch (mode) {
			case IDLE:
				throw new AutonomousManagerException("stopPlayback() called without setting mode!");
			case RECORD_RUNNING:
				throw new AutonomousManagerException("stopPlayback() called while recording is running!");
			case RECORD_WAITING:
				throw new AutonomousManagerException("stopPlayback() called in recording mode!");
			case PLAYBACK_WAITING:
				throw new AutonomousManagerException("stopPlayback() called without starting playback!");
			case PLAYBACK_RUNNING:
				mode = ManagerMode.IDLE; // maybe change to ManagerMode.PLAYBACK_WAITING?
				// playback stop stuff here
				break;
		}
	}
	
}
