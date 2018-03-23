package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class wristBack extends Command {
	
	boolean finished = false;
	
	public wristBack() {
		requires(Robot.wrist);
	}
	
	protected void initialize() {
		Robot.wrist.wristUp();
		finished = true;
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.wrist.wristStop();
	}
	
	protected void interrupted() {
		end();
	}
	
}