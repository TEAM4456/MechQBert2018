package org.usfirst.frc.team4456;

import java.util.List;
import java.util.ArrayList;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

public class AutonomousManager {
	
	private ManagerMode mode;
	
	private List<WPI_TalonSRX> talonList;
	
	private int cycleNum;
	
	private enum ManagerMode {
		IDLE,
		RECORD,
		PLAYBACK
	}
	
	public AutonomousManager() {
		
		mode = ManagerMode.IDLE;
		
		talonList = new ArrayList<>();
		
		cycleNum = 0;
		
	}
	
	public void run() {
		if (mode == ManagerMode.RECORD) {
		
		} else if (mode == ManagerMode.PLAYBACK) {
		
		}
	}
	
	public void addTalon(String name, WPI_TalonSRX talon) {
		if (mode == ManagerMode.IDLE) {
		
		}
	}
	
	public void startRecording() {
		mode = ManagerMode.RECORD;
	}
	
	public void stopRecording() {
		mode = ManagerMode.IDLE;
	}
	
	public void startPlayback() {
		mode = ManagerMode.PLAYBACK;
	}
	
	public void stopPlayback() {
		mode = ManagerMode.IDLE;
	}
	
	public ManagerMode getMode() {
		return mode;
	}
	
}
