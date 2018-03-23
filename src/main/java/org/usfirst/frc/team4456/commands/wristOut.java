package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class wristOut extends Command {
	
	boolean finished = false;
	
	public wristOut() {
		requires(Robot.wrist);
	}
	
	protected void initialize() {
		Robot.wrist.wristDown();
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
