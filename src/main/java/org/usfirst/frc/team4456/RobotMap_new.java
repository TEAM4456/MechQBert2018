package org.usfirst.frc.team4456;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SerialPort;

public class RobotMap_new {
	
	public static WPI_TalonSRX  leftDriveTalon1;
	public static WPI_TalonSRX  leftDriveTalon2; // <-- slave to leftDriveTalon1
	public static WPI_TalonSRX  rightDriveTalon1;
	public static WPI_TalonSRX  rightDriveTalon2; // <-- slave to rightDriveTalon2
	public static WPI_TalonSRX  shooterTalon;
	public static WPI_TalonSRX  winchTalon;
	public static WPI_TalonSRX  deflectorTalon;
	public static WPI_TalonSRX  intakeTalon;
	public static WPI_TalonSRX  agitatorTalon;
	public static SerialPort lidarSerial;
	public static DigitalInput deflectorSwitch;
	
	public static void init() {
		
		leftDriveTalon1 = new WPI_TalonSRX (6);
		leftDriveTalon2 = new WPI_TalonSRX (4);
		leftDriveTalon2.changeControlMode(ControlMode.Follower);
		leftDriveTalon2.set(leftDriveTalon1.getDeviceID());
		
		rightDriveTalon1 = new WPI_TalonSRX (1);
		rightDriveTalon2 = new WPI_TalonSRX (2);
		rightDriveTalon2.changeControlMode(ControlMode.Follower);
		rightDriveTalon2.set(rightDriveTalon1.getDeviceID());
		
		shooterTalon = new WPI_TalonSRX (3);
		shooterTalon.changeControlMode(ControlMode.Speed);
		shooterTalon.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		shooterTalon.setPID(.7, 0.000001, 0);
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
		
		lidarSerial = new SerialPort(9600, SerialPort.Port.kUSB);
		
		deflectorSwitch = new DigitalInput(0);
		
	}
	
}
