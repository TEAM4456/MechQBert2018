package org.usfirst.frc.team4456;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;
import com.kauailabs.navx.frc.AHRS;

public class RobotMap {
	
	public static WPI_TalonSRX leftDriveTalon1;
	public static WPI_TalonSRX leftDriveTalon2; // <-- slave to leftDriveTalon1
	public static WPI_TalonSRX rightDriveTalon1;
	public static WPI_TalonSRX rightDriveTalon2; // <-- slave to rightDriveTalon2
	public static WPI_TalonSRX shooterTalon;
	public static WPI_TalonSRX winchTalon;
	public static WPI_TalonSRX deflectorTalon;
	public static WPI_TalonSRX intakeTalon;
	public static WPI_TalonSRX agitatorTalon;
	public static SerialPort lidarSerial;
	//public static SerialPort serialPortMXP;
	public static DigitalInput deflectorSwitch;
	public static AHRS navx;
	
	public static void init() {
		
		leftDriveTalon1 = new WPI_TalonSRX (6);
		leftDriveTalon1.changeControlMode(ControlMode.PercentVbus);
		leftDriveTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//leftDriveTalon1.reverseSensor(true);
		leftDriveTalon1.setPosition(0);
		leftDriveTalon2 = new WPI_TalonSRX (4);
		leftDriveTalon2.changeControlMode(ControlMode.Follower);
		leftDriveTalon2.set(leftDriveTalon1.getDeviceID());
		
		rightDriveTalon1 = new WPI_TalonSRX (1);
		rightDriveTalon1.changeControlMode(ControlMode.PercentVbus);
		rightDriveTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		rightDriveTalon1.reverseSensor(true);
		rightDriveTalon1.setPosition(0);
		rightDriveTalon2 = new WPI_TalonSRX (2);
		rightDriveTalon2.changeControlMode(ControlMode.Follower);
		rightDriveTalon2.set(rightDriveTalon1.getDeviceID());
		
		shooterTalon = new WPI_TalonSRX (3);
		shooterTalon.changeControlMode(ControlMode.Voltage);
		shooterTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		//shooterTalon.setPID(.45, 0, 0);
		shooterTalon.reverseSensor(true);
		shooterTalon.setVoltageCompensationRampRate(0);
		shooterTalon.setInverted(true);
		
		winchTalon = new WPI_TalonSRX (8);
		
		deflectorTalon = new WPI_TalonSRX (7);
		deflectorTalon.changeControlMode(ControlMode.Position);
		deflectorTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		deflectorTalon.reverseSensor(true);
		deflectorTalon.setPID(1.5, 0, 0);
		
		intakeTalon = new WPI_TalonSRX (5);
		
		agitatorTalon = new WPI_TalonSRX (9);
		agitatorTalon.changeControlMode(ControlMode.Voltage);
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
