package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	public final WPI_TalonSRX leftDriveTalon1 = RobotMap.leftDriveTalon1;
	public final WPI_TalonSRX rightDriveTalon1 = RobotMap.rightDriveTalon1;
	
	//public final DifferentialDrive robotDrive = new DifferentialDrive(leftDriveTalon1, rightDriveTalon1);
	
	protected void initDefaultCommand() { /* setDefaultCommand(new teleopDrive()); */ }
	
	public void betterArcadeDrive(Joystick joystick) {
		
		double xValue = joystick.getRawAxis(0);
		double yValue = joystick.getRawAxis(1);
		
		// deadzone
		if (xValue > -0.2 && xValue < 0.2) {
			xValue = 0;
		}
		if (yValue > -0.2 && yValue < 0.2) {
			yValue = 0;
		}
		
		double leftValue = -(yValue - (xValue / 2));
		double rightValue = -(yValue + (xValue / 2));
		
		//robotDrive.tankDrive(leftValue, rightValue);
		
		leftDriveTalon1.set(ControlMode.Velocity, leftValue * 2900);
		rightDriveTalon1.set(ControlMode.Velocity, rightValue * 2900);
		
		SmartDashboard.putNumber("xValue", xValue);
		SmartDashboard.putNumber("yValue", yValue);
		SmartDashboard.putNumber("leftValue", leftValue);
		SmartDashboard.putNumber("rightValue", rightValue);
		
	}
	
	public void testDrive(Joystick joystick) {
		
		/*
		double xValue = joystick.getRawAxis(0);
		double yValue = joystick.getRawAxis(1);
		
		// deadzone
		if (xValue > -0.2 && xValue < 0.2) {
			xValue = 0;
		}
		if (yValue > -0.2 && yValue < 0.2) {
			yValue = 0;
		}
		
		double leftValue = -(yValue - (xValue / 2)) * 500;
		double rightValue = -(yValue + (xValue / 2)) * 500;
		*/
		
		double leftTargetVelocity = 1500;
		double rightTargetVelocity = 1500;
		
		leftDriveTalon1.set(ControlMode.Velocity, leftTargetVelocity);
		rightDriveTalon1.set(ControlMode.Velocity, rightTargetVelocity);
		
		//leftDriveTalon1.set(ControlMode.PercentOutput, 0.7);
		//rightDriveTalon1.set(ControlMode.PercentOutput, 0.7);
		
		SmartDashboard.putNumber("Left Error", leftDriveTalon1.getSelectedSensorVelocity(0) - leftTargetVelocity);
		SmartDashboard.putNumber("Right Error", rightDriveTalon1.getSelectedSensorVelocity(0) - rightTargetVelocity);
		
		/*
		SmartDashboard.putNumber("xValue", xValue);
		SmartDashboard.putNumber("yValue", yValue);
		SmartDashboard.putNumber("leftValue", leftValue);
		SmartDashboard.putNumber("rightValue", rightValue);
		*/
		
	}
	
	public void magicTestDrive(Joystick joystick) {
		
		double leftPosition = leftDriveTalon1.getSelectedSensorPosition(0);
		double rightPosition = rightDriveTalon1.getSelectedSensorPosition(0);
		
		double leftTargetPosition = leftPosition + 2500;
		double rightTargetPosition = rightPosition + 2500;
		
		if (joystick.getRawButton(1)) {
			leftDriveTalon1.set(ControlMode.MotionMagic, leftTargetPosition);
			rightDriveTalon1.set(ControlMode.MotionMagic, rightTargetPosition);
		}
		
	}
	
	public void dataDrive() {
		
		// just for gathering data for tuning PID
		
		leftDriveTalon1.set(ControlMode.PercentOutput, 1);
		rightDriveTalon1.set(ControlMode.PercentOutput, 1);
		
		
		// one rotation is 4096 units
		// average max velocity is ~2975 units/100ms
		
		/*
		
		---LEFT
		
		Battery charge: ~11.9v
		Percent Output: 100%
		Time Elapsed: 3s
		(Not touching ground)
		
		Velocities (u/100ms):
		3084.00 1
		2870.00 2
		2872.00 3
		3094.00 4
		3028.00 5
		2818.00 6
		3084.00 7
		3111.00 8
		2947.00 9
		2835.00 10
		3051.00 11
		3085.00 12
		2874.00 13
		2888.00 14
		3107.00 15
		3028.00 16
		2830.00 17
		2975.00 18
		3123.00 19
		2946.00 20
		2841.00 21
		3050.00 22
		3094.00 23
		2875.00 24
		2827.00 25
		3111.00 26
		3036.00 27
		
		Average Velocity (u/100ms): 2980.88
		
		---RIGHT
		
		Battery charge: ~11.9v
		Percent Output: 100%
		Time Elapsed: 3s
		(Not touching ground)
		
		Velocities (u/100ms):
		2881.00 1
		2997.00 2
		3050.00 3
		2919.00 4
		2918.00 5
		3044.00 6
		2931.00 7
		2890.00 8
		2967.00 9
		3071.00 10
		2965.00 11
		2895.00 12
		3016.00 13
		3059.00 14
		2917.00 15
		2927.00 16
		3055.00 17
		3011.00 18
		2890.00 19
		2978.00 20
		3036.00 21
		2962.00 22
		2899.00 23
		3027.00 24
		3069.00 25
		2917.00 26
		2924.00 27
		
		Average Velocity (u/100ms): 2974.26
		
		 */
		
	}
	
}
