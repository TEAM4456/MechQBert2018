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
	
	protected void initDefaultCommand() {}
	
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
		
		leftDriveTalon1.set(ControlMode.Velocity, leftValue * 2900);
		rightDriveTalon1.set(ControlMode.Velocity, rightValue * 2900);
		
	}
	
}
