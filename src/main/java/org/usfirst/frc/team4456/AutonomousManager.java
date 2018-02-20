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
	
	private WPI_TalonSRX[] talonArray;
	
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
	
	private enum ManagerMode { IDLE, RECORD, PLAYBACK }
	
	public AutonomousManager(int bufferSizeAdvance, double tickIntervalMs, WPI_TalonSRX[] talons) {
		
		talonArray = talons;
		
		mode = ManagerMode.IDLE;
		
		tick = 0;
		bufferSize = bufferSizeAdvance + 1;
		
		tickInterval = tickIntervalMs / 1000;
		
		tickTimer = new Timer();
		tickTimer.start();
		
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		
		autonomousData = inst.getTable("AutonomousData");
		robotData = autonomousData.getSubTable("RobotState");
		bufferData = autonomousData.getSubTable("BufferData");
		
		tickEntry = robotData.getEntry("tick");
		tickIntervalMsEntry = robotData.getEntry("tickIntervalMs");
		tickIntervalMsEntry.setDouble(tickIntervalMs);
		tickTimerEntry = robotData.getEntry("tickTimer");
		bufferSizeEntry = robotData.getEntry("bufferSize");
		bufferSizeEntry.setNumber(bufferSize);
		
		for (WPI_TalonSRX talon : talonArray) {
			generateTalonEntries(talon);
		}
		
	}
	
	private void generateTalonEntries(WPI_TalonSRX talon) {
		List<NetworkTableEntry> entries = new ArrayList<>();
		for (int i = 0; i < bufferSize; i++) {
			NetworkTableEntry entry = bufferData.getEntry(talon.getName() + "-" + i);
			entry.setDouble(0);
			entries.add(entry);
		}
	}
	
	private void writeEntryForTalon(WPI_TalonSRX talon, double value) {
		NetworkTableEntry entry = bufferData.getEntry(talon.getName() + "-" + (tick % bufferSize));
		entry.setDouble(value);
	}
	
	public void run() {
		
		double tickTimerVal = tickTimer.get();
		
		if (tickTimerVal > tickInterval) {
			
			
			
			// update tick info
			tick++;
			tickEntry.setNumber(tick);
			tickTimerEntry.setDouble(tickTimerVal);
			tickTimer.reset();
			tickTimer.start();
		}
		
	}
	
}
