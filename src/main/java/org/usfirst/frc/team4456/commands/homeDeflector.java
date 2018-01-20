package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class homeDeflector extends Command {
	
	boolean finished;
	
	public homeDeflector() { requires(Robot.deflector); }
	
	protected void initialize() { finished = false; }
	
	protected boolean isFinished() { return finished; }
	
	protected void execute() {
		
		if (!RobotMap.deflectorSwitch.get()) {
			Robot.deflector.raiseDeflector();
		} else {
			RobotMap.deflectorTalon.setSelectedSensorPosition(0,0,0);
			finished = true;
		}
		
	}
	
	protected void end() { RobotMap.deflectorTalon.set(0); }
	
	protected void interrupted() { end(); }
	
}
