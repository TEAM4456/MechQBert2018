package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class toggleShooter extends Command {
	
	boolean running;
	
	public toggleShooter() {
		requires(Robot.shooter);
		requires(Robot.agitator);
	}
	
	protected void initialize() { running = false; }
	
	protected boolean isFinished() { return isTimedOut(); }
	
	public synchronized void start() {
		running = true;
		Robot.shooter.startShooter();
		Robot.agitator.startAgitator();
	}
	
	public synchronized void cancel() { end(); }
	
	protected void end() {
		Robot.shooter.stopShooter();
		Robot.agitator.stopAgitator();
		running = false;
	}
	
	protected void interrupted() { end(); }
	
	public synchronized boolean isRunning() { return running; }
	
}
