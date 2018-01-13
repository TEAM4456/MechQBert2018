package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class toggleIntake extends Command {
	
	boolean running;
	
	public toggleIntake() { requires(Robot.intake); }
	
	protected void initialize() { running = false; }
	
	protected boolean isFinished() { return isTimedOut(); }
	
	public synchronized void start() {
		running = true;
		Robot.intake.startIntake();
	}
	
	public synchronized void cancel() { end(); }
	
	protected void end() {
		Robot.intake.stopIntake();
		running = false;
	}
	
	protected void interrupted() { end(); }
	
	public synchronized boolean isRunning() { return running; }
	
}
