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
	public static WPI_TalonSRX vertActTalon;
	public static WPI_TalonSRX diagActTalon;
	public static WPI_TalonSRX wristTalon;
	public static WPI_TalonSRX clawTalon;


	public static AHRS navx;
	
	public static void init() {
		
		leftDriveTalon1 = new WPI_TalonSRX (3);
		leftDriveTalon1.set(ControlMode.Velocity, 0);
		leftDriveTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		//leftDriveTalon1.reverseSensor(true);
		leftDriveTalon1.setSelectedSensorPosition(0, 0, 0);
		leftDriveTalon2 = new WPI_TalonSRX (4);
		leftDriveTalon2.set(ControlMode.Follower, leftDriveTalon1.getDeviceID());
		
		rightDriveTalon1 = new WPI_TalonSRX (1);
		rightDriveTalon1.set(ControlMode.Velocity, 0);
		rightDriveTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rightDriveTalon1.setInverted(true);
		rightDriveTalon1.setSelectedSensorPosition(0, 0, 0);
		rightDriveTalon2 = new WPI_TalonSRX (2);
		rightDriveTalon2.set(ControlMode.Follower, rightDriveTalon1.getDeviceID());
		
		vertActTalon = new WPI_TalonSRX(5);
		vertActTalon.set(ControlMode.Position, 0);
		vertActTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

		diagActTalon = new WPI_TalonSRX(6);
		diagActTalon.set(ControlMode.Position, 0);
		diagActTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

		wristTalon = new WPI_TalonSRX(7);
		wristTalon.set(ControlMode.Position, 0);
		wristTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

		clawTalon = new WPI_TalonSRX(8);
		clawTalon.set(ControlMode.Position, 0);
		clawTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);

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
