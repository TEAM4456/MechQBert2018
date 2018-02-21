package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class winchUp extends Command {
	
	public winchUp() { requires(Robot.winch); }
	
	protected void initialize() { Robot.winch.winchUp(); }
	
	protected boolean isFinished() { return true; }
	
	protected void end() { Robot.winch.winchStop(); }
	
	protected void interrupted() { end(); }
	
}
