package org.usfirst.frc.team4456.autonomous;

import org.usfirst.frc.team4456.Robot;
import org.usfirst.frc.team4456.Globals;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import openrio.powerup.MatchData;

public class AutonomousHandler {
	
	private AutonomousManager autonomousManager;
	private SendableChooser<Boolean> testingSelector;
	private SendableChooser<String> modeSelector;
	
	private boolean robotEnabled;
	
	private boolean recordingRunning;
	private boolean playbackRunning;
	
	private String lastHandlerMessage;
	private String lastManagerException;
	private int handlerMessageRepeatCount;
	private int managerExceptionRepeatCount;
	
	private int buttonNumber;
	
	private final MatchData.OwnedSide LEFT = MatchData.OwnedSide.LEFT;
	private final MatchData.OwnedSide RIGHT = MatchData.OwnedSide.RIGHT;
	
	public AutonomousHandler(AutonomousManager am, int controlButton) {
		
		autonomousManager = am;
		
		buttonNumber = controlButton;
		
		robotEnabled = false;
		
		recordingRunning = false;
		playbackRunning = false;
		
		lastHandlerMessage = "";
		lastManagerException = "";
		handlerMessageRepeatCount = 1;
		managerExceptionRepeatCount = 1;
		
		SmartDashboard.putString("AutonomousHandler message", "");
		SmartDashboard.putString("AutonomousManager exception", "");
		SmartDashboard.putString("Recording name", "");
		SmartDashboard.putBoolean("RECORDING", false);
		SmartDashboard.putBoolean("PLAYBACK", false);
		
		testingSelector = new SendableChooser<>();
		testingSelector.addDefault("DISABLED", false);
		testingSelector.addObject("ENABLED", true);
		SmartDashboard.putData("Autonomous Testing", testingSelector);
		
		modeSelector = new SendableChooser<>();
		modeSelector.addDefault("(IDLE)", "IDLE");
		modeSelector.addObject("RECORD", "RECORD");
		modeSelector.addObject("PLAYBACK", "PLAYBACK");
		SmartDashboard.putData("Testing Mode Selection", modeSelector);
		
	}
	
	public void run() {
		
		if (recordingRunning && !isManagerInRecording()) {
			putHandlerMessage("WARNING: manager stopped recording unexpectedly. Recording cancelled.");
			// (AutonomousManager cancels the recording itself)
			recordingRunning = false;
		} else if (playbackRunning && !isMangerInPlayback()) {
			putHandlerMessage("WARNING: manager stopped playback.");
			playbackRunning = false;
		}
		
		SmartDashboard.putBoolean("RECORDING", recordingRunning);
		SmartDashboard.putBoolean("PLAYBACK", playbackRunning);
		
		boolean buttonPressed = Robot.controls.joystick.getRawButtonPressed(buttonNumber);
		
		
		if (buttonPressed && robotEnabled) {
			
			boolean isTesting = testingSelector.getSelected();
			if (isTesting) {
				String testingMode = modeSelector.getSelected();
				
				if (testingMode.equals("RECORD") && isMangerInPlayback()) {
					putHandlerMessage("ERROR: requested recording mode while playback is running!");
					stopPlayback();
				} else if (testingMode.equals("PLAYBACK") && isManagerInRecording()) {
					putHandlerMessage("ERROR: requested playback mode while recording is running! Cancelling recording.");
					stopRecording(true);
				}
				
				if (testingMode.equals("RECORD")) {
					if (!recordingRunning) {
						startRecording();
					} else {
						stopRecording(false);
					}
				} else if (testingMode.equals("PLAYBACK")) {
					if (!playbackRunning) {
						startTestingPlayback();
					} else {
						stopPlayback();
					}
				}
			} else {
				putHandlerMessage("WARNING: control button activated while testing is disabled!");
			}
			
		}
		
		try {
			autonomousManager.run();
		} catch (AutonomousManagerException ex) {
			putManagerException(ex);
		}
		
	}
	
	public void startCompetitionAuto(String robotPos) {
		/* TODO: probably redo available choices, allow for explicit priorities */
		switch (robotPos) {
			case "left":
				startCompetitionPlayback("Baseline");
				// uncomment if we are able to do both switch and scale
				/*if (switchSide == LEFT && scaleSide == LEFT) {
					// start left, do switch and scale left
					//startCompetitionPlayback("L-both");
				} else*/
				/*
				if (scaleSide == LEFT) {
					// start left, do scale left
					//startCompetitionPlayback("L-scale");
				} else if (switchSide == LEFT) {
					// start left, do switch left
					//startCompetitionPlayback("L-switch");
				} else {
					// neither switch nor scale are left, do baseline or other
					//startCompetitionPlayback("L-fallback");
				}
				*/
				break;
			case "middle":
				if (Globals.switchSide == LEFT) {
					// start middle, do switch left
					//startCompetitionPlayback("M-switch-left");
				} else if (Globals.switchSide == RIGHT) {
					// start middle, do switch right
					//startCompetitionPlayback("M-switch-right");
				} /* else { logic machine br0ke } */
				break;
			case "right":
				// uncomment if we are able to do both switch and scale
				/*if (switchSide == RIGHT && scaleSide == RIGHT) {
					// start right, do switch and scale right
					//startCompetitionPlayback("R-both");
				} else*/
				if (Globals.scaleSide == RIGHT) {
					// start right, do scale right
					startCompetitionPlayback("R-scale");
				} else if (Globals.switchSide == RIGHT) {
					// start right, do switch right
					startCompetitionPlayback("R-switch");
				} else {
					// neither switch nor scale are right, do baseline or other
					startCompetitionPlayback("Baseline");
				}
				break;
		}
	}
	
	public void updateEnabledStatus(boolean enabled) {
		robotEnabled = enabled;
		if (!robotEnabled) {
			if (isManagerInRecording()) {
				putHandlerMessage("WARNING: robot disabled while recording is running! Cancelling recording.");
				stopRecording(true);
				recordingRunning = false;
				SmartDashboard.putBoolean("RECORDING", false);
			} else if (isMangerInPlayback()) {
				putHandlerMessage("WARNING: robot disabled while playback is running!");
				stopPlayback();
				playbackRunning = false;
				SmartDashboard.putBoolean("PLAYBACK", false);
			}
		}
		autonomousManager.updateEnabledStatus(enabled);
	}
	
	private void startRecording() {
		String recordingName = SmartDashboard.getString("Recording name", "");
		if (!recordingName.equals("")) {
			recordingRunning = true;
			try {
				autonomousManager.startRecording(recordingName);
			} catch (AutonomousManagerException ex) {
				putManagerException(ex);
			}
		} else {
			putHandlerMessage("ERROR: tried to start recording without name!");
		}
	}
	
	private void stopRecording(boolean cancelRecording) {
		recordingRunning = false;
		try {
			autonomousManager.stopRecording(cancelRecording);
		} catch (AutonomousManagerException ex) {
			putManagerException(ex);
		}
	}
	
	private void startCompetitionPlayback(String recordingName) {
		playbackRunning = true;
		/*
		try {
			Thread.sleep(1000);
		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage()); // not really gonna do anything
		}
		*/
		try {
			autonomousManager.startPlayback(recordingName);
		} catch (AutonomousManagerException ex) {
			putManagerException(ex);
		}
	}
	
	private void startTestingPlayback() {
		String recordingName = SmartDashboard.getString("Recording name", "");
		if (!recordingName.equals("")) {
			playbackRunning = true;
			try {
				autonomousManager.startPlayback(recordingName);
			} catch (AutonomousManagerException ex) {
				putManagerException(ex);
			}
		} else {
			putHandlerMessage("ERROR: tried to start playback without name!");
		}
	}
	
	private void stopPlayback() {
		playbackRunning = false;
		try {
			autonomousManager.stopPlayback();
		} catch (AutonomousManagerException ex) {
			putManagerException(ex);
		}
	}
	
	public boolean isRecordingRunning() {
		return recordingRunning;
	}
	
	public boolean isPlaybackRunning() {
		return playbackRunning;
	}
	
	private void putHandlerMessage(String message) {
		if (!message.equals(lastHandlerMessage)) {
			lastHandlerMessage = message;
			SmartDashboard.putString("AutonomousHandler message", message);
			handlerMessageRepeatCount = 1;
		} else {
			handlerMessageRepeatCount++;
			SmartDashboard.putString("AutonomousHandler message", message +
					" (x" + handlerMessageRepeatCount + ")");
		}
	}
	
	private void putManagerException(AutonomousManagerException ex) {
		if (!ex.getMessage().equals(lastManagerException)) {
			lastManagerException = ex.getMessage();
			SmartDashboard.putString("AutonomousManager exception", ex.getMessage());
			managerExceptionRepeatCount = 1;
		} else {
			managerExceptionRepeatCount++;
			SmartDashboard.putString("AutonomousManager exception", ex.getMessage() +
					" (x" + managerExceptionRepeatCount + ")");
		}
	}
	
	private boolean isManagerInRecording() {
		return autonomousManager.isRecordRunning();
	}
	
	private boolean isMangerInPlayback() {
		return autonomousManager.isPlaybackRunning();
	}
	
}
