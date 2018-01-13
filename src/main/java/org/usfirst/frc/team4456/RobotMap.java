package org.usfirst.frc.team4456;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.AHRS;

public class RobotMap {
	
	public static CANTalon leftDriveTalon1;
	public static CANTalon leftDriveTalon2; // <-- slave to leftDriveTalon1
	public static CANTalon rightDriveTalon1;
	public static CANTalon rightDriveTalon2; // <-- slave to rightDriveTalon2
	public static CANTalon shooterTalon;
	public static CANTalon winchTalon;
	public static CANTalon deflectorTalon;
	public static CANTalon intakeTalon;
	public static CANTalon agitatorTalon;
	public static SerialPort lidarSerial;
	//public static SerialPort serialPortMXP;
	public static DigitalInput deflectorSwitch;
	public static AHRS navx;
	
	public static void init() {
		
		leftDriveTalon1 = new CANTalon(6);
		leftDriveTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftDriveTalon1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		//leftDriveTalon1.reverseSensor(true);
		leftDriveTalon1.setPosition(0);
		leftDriveTalon2 = new CANTalon(4);
		leftDriveTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftDriveTalon2.set(leftDriveTalon1.getDeviceID());
		
		rightDriveTalon1 = new CANTalon(1);
		rightDriveTalon1.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightDriveTalon1.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightDriveTalon1.reverseSensor(true);
		rightDriveTalon1.setPosition(0);
		rightDriveTalon2 = new CANTalon(2);
		rightDriveTalon2.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightDriveTalon2.set(rightDriveTalon1.getDeviceID());
		
		shooterTalon = new CANTalon(3);
		shooterTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
		shooterTalon.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		//shooterTalon.setPID(.45, 0, 0);
		shooterTalon.reverseSensor(true);
		shooterTalon.setVoltageCompensationRampRate(0);
		shooterTalon.setInverted(true);
		
		winchTalon = new CANTalon(8);
		
		deflectorTalon = new CANTalon(7);
		deflectorTalon.changeControlMode(CANTalon.TalonControlMode.Position);
		deflectorTalon.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		deflectorTalon.reverseSensor(true);
		deflectorTalon.setPID(1.5, 0, 0);
		
		intakeTalon = new CANTalon(5);
		
		agitatorTalon = new CANTalon(9);
		agitatorTalon.changeControlMode(CANTalon.TalonControlMode.Voltage);
		agitatorTalon.setVoltageCompensationRampRate(0);
		agitatorTalon.setSafetyEnabled(false);
		
		try {
			lidarSerial = new SerialPort(9600, SerialPort.Port.kUSB);
		} catch (Exception ex) {
			lidarSerial = null;
			System.out.println("Exception: " + ex);
		}
		
		deflectorSwitch = new DigitalInput(0);

		//NAVX init
		try
		{
			//serialPortMXP = new SerialPort(57600, SerialPort.Port.kMXP);
			byte updateRateHz = 50;
			navx = new AHRS(SerialPort.Port.kMXP, AHRS.SerialDataType.kProcessedData, updateRateHz);
		}
		catch(Exception ex)
		{
			System.out.println("ERROR!: NAVX INIT" + "\n" + ex);
		}
		
	}
	
}
