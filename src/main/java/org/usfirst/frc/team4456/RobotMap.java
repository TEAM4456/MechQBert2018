package org.usfirst.frc.team4456;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class RobotMap {
	
	public static WPI_TalonSRX leftDriveTalon1;
	public static WPI_TalonSRX leftDriveTalon2; // <-- slave to leftDriveTalon1
	public static WPI_TalonSRX rightDriveTalon1;
	public static WPI_TalonSRX rightDriveTalon2; // <-- slave to rightDriveTalon2
	public static WPI_TalonSRX armTalon;
	public static WPI_TalonSRX winchTalon1;
	public static WPI_TalonSRX winchTalon2;
	public static WPI_TalonSRX wristTalon;
	public static WPI_TalonSRX clawTalon;
	
	public static AHRS navx;
	
	public static void init() {
		
		leftDriveTalon1 = new WPI_TalonSRX(3);
		leftDriveTalon1.set(ControlMode.PercentOutput, 0);
		leftDriveTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		leftDriveTalon1.setInverted(true);
		//leftDriveTalon1.setSensorPhase(false);
		leftDriveTalon1.setSelectedSensorPosition(0, 0, 0);
		leftDriveTalon1.configClosedloopRamp(0.1, 0);
		//leftDriveTalon1.configMotionAcceleration(2900, 0);
		//leftDriveTalon1.configMotionCruiseVelocity(2900, 0);
		//leftDriveTalon1.configAllowableClosedloopError(0, 100, 0);
		//leftDriveTalon2 = new WPI_TalonSRX(6);
		//leftDriveTalon2.setInverted(true);
		//leftDriveTalon2.set(ControlMode.Follower, leftDriveTalon1.getDeviceID());
		
		rightDriveTalon1 = new WPI_TalonSRX(1);
		rightDriveTalon1.set(ControlMode.PercentOutput, 0);
		rightDriveTalon1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rightDriveTalon1.setInverted(true);
		rightDriveTalon1.setSensorPhase(true);
		rightDriveTalon1.setSelectedSensorPosition(0, 0, 0);
		rightDriveTalon1.configClosedloopRamp(0.1, 0);
		//rightDriveTalon1.configMotionAcceleration(2900, 0);
		//rightDriveTalon1.configMotionCruiseVelocity(2900, 0);
		///rightDriveTalon1.configAllowableClosedloopError(0, 50, 0);
		//rightDriveTalon2 = new WPI_TalonSRX(2);
		//rightDriveTalon2.setInverted(true);
		//rightDriveTalon2.set(ControlMode.Follower, rightDriveTalon1.getDeviceID());
		
		// PID CONTROL
		
		// F-gain: F-gain = ([Percent Output] * 1023) / [Velocity]
		
		leftDriveTalon1.config_kP(0, Globals.leftDriveP, 0);
		leftDriveTalon1.config_kI(0, Globals.leftDriveI, 0);
		leftDriveTalon1.config_kD(0, Globals.leftDriveD, 0);
		leftDriveTalon1.config_kF(0, Globals.leftDriveF, 0);
		
		rightDriveTalon1.config_kP(0, Globals.rightDriveP, 0);
		rightDriveTalon1.config_kI(0, Globals.rightDriveI, 0);
		rightDriveTalon1.config_kD(0, Globals.rightDriveD, 0);
		rightDriveTalon1.config_kF(0, Globals.rightDriveF, 0);
		
		armTalon = new WPI_TalonSRX(5);
		armTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		armTalon.set(ControlMode.Position, 0);
		//armTalon.configPeakOutputReverse(-0.5, 10); // TESTING, pacify bmac
		//armTalon.configPeakOutputForward(0.5, 10);  // TESTING, pacify bmac
		//armTalon.setInverted(true);
		armTalon.setSensorPhase(true);
		
		winchTalon1 = new WPI_TalonSRX(10);
		winchTalon1.setInverted(true);
		winchTalon1.set(ControlMode.PercentOutput, 0);
		
		wristTalon = new WPI_TalonSRX(7);
		//wristTalon.setInverted(true);
		//wristTalon.setSensorPhase(true);
		wristTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		wristTalon.set(ControlMode.Position, 0);
		//wristTalon.configOpenloopRamp(.5, 0);
		
		clawTalon = new WPI_TalonSRX(9);
		clawTalon.setInverted(true);
		clawTalon.set(ControlMode.PercentOutput, 0);
		clawTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		
		
		//NAVX init
		/*try
		{
			//serialPortMXP = new SerialPort(57600, SerialPort.Port.kMXP);
			byte updateRateHz = 50;
			navx = new AHRS(SerialPort.Port.kMXP, AHRS.SerialDataType.kProcessedData, updateRateHz);
		} catch (Exception ex) {
			System.out.println("ERROR!: NAVX INIT" + "\n" + ex);
		}*/
		
		
	}
	
}
