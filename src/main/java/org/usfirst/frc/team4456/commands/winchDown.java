package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class winchDown extends Command {
	
	public winchDown() {
		requires(Robot.winch);
	}
	
	protected void initialize() {
		Robot.winch.winchDown();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.winch.winchStop();
	}
	
	protected void interrupted() {
		end();
	}
	
	
}
