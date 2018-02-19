package org.usfirst.frc.team4456;

import java.util.List;
import java.util.ArrayList;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

public class AutonomousManager {
	
	private ManagerMode mode;
	
	private List<WPI_TalonSRX> talonList;
	
	private int cycleNum;
	
	NetworkTable table;
	
	private enum ManagerMode { IDLE, RECORD, PLAYBACK }
	
	public AutonomousManager() {
		
		mode = ManagerMode.IDLE;
		
		talonList = new ArrayList<>();
		
		cycleNum = 0;
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		
		table = inst.getTable("datatable");
		
		
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
	
	public ManagerMode getMode() {
		return mode;
	}
	
}
