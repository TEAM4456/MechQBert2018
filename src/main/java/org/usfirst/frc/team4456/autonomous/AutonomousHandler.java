package org.usfirst.frc.team4456.autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousHandler {
	
	private AutonomousManager autonomousManager;
	
	private boolean recordingRunning;
	private boolean playbackRunning;
	
	public AutonomousHandler(AutonomousManager am) {
		
		autonomousManager = am;
		
		SmartDashboard.putString("AutonomousHandler message", "");
		SmartDashboard.putString("Recording name", "");
		SmartDashboard.putBoolean("RECORDING", false);
		SmartDashboard.putBoolean("PLAYBACK", false);
		
	}
	
	public void run() {
		SmartDashboard.putBoolean("RECORDING", recordingRunning);
		SmartDashboard.putBoolean("PLAYBACK", playbackRunning);
	}
	
	public void startRecording() {
		String recordingName = SmartDashboard.getString("Recording name", "");
		if (!recordingName.equals("")) {
			recordingRunning = true;
			try {
				autonomousManager.startRecording(recordingName);
			} catch (AutonomousManagerException ex) {
				System.out.println(ex);
			}
		} else {
			SmartDashboard.putString("AutonomousHandler message", "ERROR: tried to start recording without name!");
		}
	}
	
	
	
}
