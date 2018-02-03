package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armDown extends Command {
    boolean finished;

    public armDown() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armDown();
    }

    protected boolean isFinished() { return finished; }

    protected void end() {Robot.arm.armVertStop();}

    protected void interrupted() { end(); }
}
