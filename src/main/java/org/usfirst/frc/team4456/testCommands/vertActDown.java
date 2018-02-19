package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class vertActDown extends Command {

    public vertActDown() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armDown();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.arm.armVertStop();}

    protected void interrupted() { end(); }
}
