package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class clawClose extends Command {
	
	boolean finished = true; // never false, remove and "return true;" instead?
	
	public clawClose() { requires(Robot.claw); }
	
	protected void initialize() { Robot.claw.clawClose(); }
	
	protected boolean isFinished() { return finished; }
	
	protected void end() { Robot.claw.clawStop(); }
	
	protected void interrupted() { end(); }
	
}


