package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class autoDistance_VBus extends Command {
	
	boolean finished;
	double setPoint;
	double speed;
	boolean forward;
	
	public autoDistance_VBus(double setPoint, double speed, boolean forward) {
		requires(Robot.drive);
		finished = false;
		this.setPoint = setPoint;
		this.speed = speed;
		this.forward = forward;
	}
	
	protected void initialize() {}
	
	protected boolean isFinished() { return finished; }
	
	protected void execute() {
		if (forward) {
			Robot.drive.robotDrive.tankDrive(-speed, -speed);
			if (RobotMap.leftDriveTalon1.getPosition() >= setPoint) {
				finished = true;
			}
		} else {
			Robot.drive.robotDrive.tankDrive(speed, speed);
			if (RobotMap.leftDriveTalon1.getPosition() <= setPoint) {
				finished = true;
			}
		}
	}
	
	protected void end() { Robot.drive.robotDrive.tankDrive(0, 0); }
	
	protected void interrupted() { end(); }
}
