package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class armUp extends Command {
	
	public armUp() {
		requires(Robot.arm);
	}
	
	protected void initialize() {
		Robot.arm.armUp();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.arm.armStop();
	}
	
	protected void interrupted() {
		end();
	}
	
}
