package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class clawClose extends Command {
	
	public clawClose() {
		requires(Robot.claw);
	}
	
	protected void initialize() {
		Robot.claw.clawClose();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.claw.clawStop();
	}
	
	protected void interrupted() {
		end();
	}
	
}


