package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class armDown extends Command {
	
	public armDown() {
		requires(Robot.arm);
	}
	
	protected void initialize() {
		Robot.arm.armDown();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		Robot.arm.armStop();
	}
	
	protected void interrupted() {
		end();
	}
	
}
