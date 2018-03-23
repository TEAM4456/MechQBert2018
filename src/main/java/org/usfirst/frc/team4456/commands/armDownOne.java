package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class armDownOne extends Command {
	
	public armDownOne() {
		requires(Robot.arm);
	}
	
	protected void initialize() {
		Robot.arm.armDownOne();
		Robot.wrist.wristDownOne();
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {
	}
	
	protected void interrupted() {
		end();
	}
}
