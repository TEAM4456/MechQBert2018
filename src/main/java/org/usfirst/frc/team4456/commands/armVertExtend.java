package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armVertExtend extends Command {
	
	
	public armVertExtend() { requires(Robot.arm); }
	
	protected void initialize() {
		Robot.arm.armUp();
	}
	
	protected boolean isFinished() { return false; }
	
	protected void end() {Robot.arm.armVertStop();}
	
	protected void interrupted() { end(); }
	
}
