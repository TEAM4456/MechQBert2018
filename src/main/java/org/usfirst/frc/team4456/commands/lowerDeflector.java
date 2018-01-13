package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class lowerDeflector extends Command {
	
	/*
	THIS COMMAND IS PROBABLY TEMPORARY
	PLANNED FUNCTIONALITY IS SWAPPING BETWEEN 2 SPECIFIC PID POSITIONS FOR HIGH GOAL/LOW GOAL
	*/
	
	boolean finished;
	
	public lowerDeflector() { requires(Robot.deflector); }
	
	protected void initialize() { finished = false; }
	
	protected boolean isFinished() { return !finished; }
	
	protected void execute() {
		Robot.deflector.lowerDeflector();
		finished = true;
	}
	
	protected void end() { Robot.deflector.stopDeflector(); }
	
	protected void interrupted() { end(); }
	
}
