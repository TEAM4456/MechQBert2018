package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	
	public final WPI_TalonSRX leftDriveTalon1 = RobotMap.leftDriveTalon1;
	public final WPI_TalonSRX rightDriveTalon1 = RobotMap.rightDriveTalon1;
	
	public final DifferentialDrive robotDrive = new DifferentialDrive(leftDriveTalon1, rightDriveTalon1);
	
	protected void initDefaultCommand() { /* setDefaultCommand(new teleopDrive()); */ }
	
	public void betterArcadeDrive(Joystick joystick) {
		
		double xValue = joystick.getRawAxis(0);
		double yValue = joystick.getRawAxis(1);
		
		robotDrive.tankDrive(yValue - (xValue / 2), yValue + (xValue / 2));
		
	}
	
}
