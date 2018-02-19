package org.usfirst.frc.team4456;

import java.util.List;
import java.util.ArrayList;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;

public class AutonomousManager {
	
	private ManagerMode mode;
	
	private List<WPI_TalonSRX> talonList;
	
	private int tick;
	
	private Timer tickTimer;
	
	private NetworkTable autonomousData;
	private NetworkTable robotData;
	private NetworkTable bufferData;
	
	private NetworkTableEntry tickEntry;
	
	private NetworkTableEntry testEntry;
	
	private enum ManagerMode { IDLE, RECORD, PLAYBACK }
	
	public AutonomousManager() {
		
		mode = ManagerMode.IDLE;
		
		tick = 0;
		
		talonList = new ArrayList<>();
		
		tickTimer = new Timer();
		tickTimer.start();
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		
		autonomousData = inst.getTable("AutonomousData");
		robotData = autonomousData.getSubTable("RobotState");
		bufferData = autonomousData.getSubTable("BufferData");
		
		tickEntry = robotData.getEntry("tick");
		testEntry = bufferData.getEntry("test");
		
	}
	
	public void run() {
		
		if (tickTimer.get() > 0.1) {
			tick++;
			tickEntry.setNumber(tick);
			tickTimer.reset();
			tickTimer.start();
		}
		double velocity = RobotMap.rightDriveTalon1.getSelectedSensorVelocity(0);
		testEntry.setDouble(velocity);
		
	}
	
	public void addTalon(String name, WPI_TalonSRX talon) {
		if (mode == ManagerMode.IDLE) {
			talonList.add(talon);
		}
	}
	
	public ManagerMode getMode() { return mode; }
	
}
