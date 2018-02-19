package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class vertActUp extends Command {

    public vertActUp() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armUp();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.arm.armVertStop();}

    protected void interrupted() { end(); }
}
