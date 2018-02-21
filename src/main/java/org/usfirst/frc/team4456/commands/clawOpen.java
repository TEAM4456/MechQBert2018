package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.subsystems.Claw;

public class clawOpen extends Command {
	
	boolean finished = true; // never false, remove and "return true;" instead?
	
	public clawOpen() { requires(Robot.claw); }
	
	protected void initialize() { Robot.claw.clawOpen(); }
	
	protected boolean isFinished() { return finished; }
	
	protected void end() { Robot.claw.clawStop(); }
	
	protected void interrupted() { end(); }
	
}

