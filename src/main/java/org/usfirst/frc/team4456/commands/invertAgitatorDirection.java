package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class invertAgitatorDirection extends Command {
	
	boolean finished;
	
	public invertAgitatorDirection() { requires(Robot.agitator); }
	
	protected void initialize() { finished = false; }
	
	protected boolean isFinished() { return !finished; }
	
	protected void execute() {
		RobotMap.agitatorTalon.reverseOutput(true);
		finished = true;
	}
	
	protected void end() { RobotMap.agitatorTalon.reverseOutput(false); }
	
	protected void interrupted() { end(); }
	
}
