package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

public class autoRunShooterForTime extends Command {
	
	Timer timer;
	
	boolean finished;
	long time;
	
	public autoRunShooterForTime(long time) {
		timer = new Timer();
		this.time = time;
		finished = false;
	}
	
	protected void initialize() {
		timer.reset();
		timer.start();
		Robot.shooter.startShooter();
		Robot.agitator.startAgitator();
	}
	
	protected boolean isFinished() { return timer.hasPeriodPassed(time); }
	
	protected void execute() {}
	
	protected void end() {
		Robot.shooter.stopShooter();
		Robot.agitator.stopAgitator();
	}
	
	protected void interrupted() { end(); }
	
}
