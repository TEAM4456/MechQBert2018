package org.usfirst.frc.team4456.subsystems;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;


public class DriveAngle /*extends PIDSubsystem*/ { // This system extends PIDSubsystem
/*
	public final WPI_TalonSRX leftDriveTalon1 = RobotMap.leftDriveTalon1;
	public final WPI_TalonSRX rightDriveTalon1 = RobotMap.rightDriveTalon1;

	public DriveAngle() {
		super("DriveAngle", 0.015, 0.0, 0.0);// The constructor passes a name for the subsystem and the P, I and D constants that are sueed when computing the motor output
		setAbsoluteTolerance(5);
		getPIDController().setContinuous(false);
	}

	public void initDefaultCommand() {
	}

	protected double returnPIDInput() {
		return RobotMap.navx.getYaw(); // returns the sensor value that is providing the feedback for the system
	}

	protected void usePIDOutput(double output) {
		leftDriveTalon1.pidWrite(-output); // this is where the computed output value fromthe PIDController is applied to the motor
		rightDriveTalon1.pidWrite(-output);
	}
	*/
}
