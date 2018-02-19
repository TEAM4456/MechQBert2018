package org.usfirst.frc.team4456;

import java.util.List;
import java.util.ArrayList;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class AutonomousManager {
	
	private ManagerMode mode;
	
	private List<WPI_TalonSRX> talonList;
	
	private int tick;
	
	NetworkTable autonomousData;
	NetworkTable robotData;
	NetworkTable bufferData;
	
	NetworkTableEntry tickEntry;
	NetworkTableEntry testEntry;
	
	private enum ManagerMode { IDLE, RECORD, PLAYBACK }
	
	public AutonomousManager() {
		
		mode = ManagerMode.IDLE;
		
		tick = 0;
		
		talonList = new ArrayList<>();
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		
		autonomousData = inst.getTable("AutonomousData");
		robotData = autonomousData.getSubTable("RobotState");
		bufferData = autonomousData.getSubTable("BufferData");
		
		tickEntry = robotData.getEntry("tick");
		tickEntry.setNumber(0);
		
		testEntry = bufferData.getEntry("test");
		testEntry.setDouble(100);
		
	}
	
	public void run() {
	
		tick++;
		testEntry.setDouble(tick);
	
	}
	
	public void addTalon(String name, WPI_TalonSRX talon) {
		if (mode == ManagerMode.IDLE) {
			talonList.add(talon);
		}
	}
	
	public ManagerMode getMode() { return mode; }
	
}
