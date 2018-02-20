package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class teleopDrive extends Command {

	boolean finished;
	
	public teleopDrive() {
		requires(Robot.drive);
	}
	
	protected void initialize() {
		finished = false;
	}
	
	protected boolean isFinished() {
		return finished;
	}
	
	protected void execute() {
		Robot.drive.betterArcadeDrive(Robot.controls.joystick);
		finished = true;
	}
	
	protected void end() {}

	protected void interrupted() {
		end();
	}
}
