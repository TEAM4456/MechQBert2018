package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.subsystems.Winch;

public class winchDown extends Command {
	boolean finished;
	
	public winchDown() {
		requires(Robot.winch);
	}
	
	protected void initialize() {
		Robot.winch.winchDown();
	}
	
	protected boolean isFinished() {
		return finished;
	}
	
	protected void end() {
		Robot.winch.winchStop();
	}
	
	protected void interrupted() {
		end();
	}
	
	
}
