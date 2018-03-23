package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Drive extends Subsystem {
	
	private final WPI_TalonSRX leftDriveTalon1 = RobotMap.leftDriveTalon1;
	private final WPI_TalonSRX rightDriveTalon1 = RobotMap.rightDriveTalon1;
	
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
