package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class toggleWinchReverse extends Command {
	
	boolean running;
	
	public toggleWinchReverse() { requires(Robot.winch); }
	
	protected void initialize() { running = false; }
	
	protected boolean isFinished() { return isTimedOut(); }
	
	public synchronized void start() {
		running = true;
		Robot.winch.startWinchReverse();
	}
	
	public synchronized void cancel() { end(); }
	
	protected void end() {
		Robot.winch.stopWinch();
		running = false;
	}
	
	protected void interrupted() { end(); }
	
	public synchronized boolean isRunning() { return running; }
	
}
