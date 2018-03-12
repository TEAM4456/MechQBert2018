package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armDown extends Command {
	
	public armDown() { requires(Robot.arm); }
	
	protected void initialize() {
		Robot.arm.armDown();
		Robot.arm.armDiagRetract();
	}
	
	protected boolean isFinished() { return false; }
	
	protected void end() {
		Robot.arm.armVertStop();
		Robot.arm.armDiagStop();
	}
	
	protected void interrupted() { end(); }

}
