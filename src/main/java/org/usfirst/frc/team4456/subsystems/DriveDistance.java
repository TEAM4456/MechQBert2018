package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import org.usfirst.frc.team4456.RobotMap;


public class DriveDistance extends PIDSubsystem { // This system extends PIDSubsystem

	public WPI_TalonSRX DriveTalon1;
	public WPI_TalonSRX DriveTalon2;
	// you will have to define the DriveTalon when instantiating this class,
	// e.g.
	// driveDistanceLeft = new DriveDistance();
	// driveDistanceLeft.DriveTalon = RobotMap.leftDriveTalon1;

	public DriveDistance() {
		super("DriveDistance", 0.002, 0.0, 0.0);// The constructor passes a name for the subsystem and the P, I and D constants that are sueed when computing the motor output
		setAbsoluteTolerance(10);
		getPIDController().setContinuous(false);
		setOutputRange(0.0, 0.25);
	}

	public void initDefaultCommand() {
	}

	protected double returnPIDInput() {
		return DriveTalon1.getSelectedSensorPosition(0); // returns the sensor value that is providing the feedback for the system
	}

	protected void usePIDOutput(double output) {
		/*
		double limitedOutput;
		if (output > 0) {
			limitedOutput = Math.min(output, .25);
		} else {
			limitedOutput = Math.max(output, -.25);
		}
		*/
		DriveTalon1.pidWrite(-output); // this is where the computed output value fromthe PIDController is applied to the motor
		DriveTalon2.pidWrite(output);
	}
}
