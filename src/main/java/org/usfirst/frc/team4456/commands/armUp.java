package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armUp extends Command {
    boolean finished;

    public armUp() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armUp();
    }

    protected boolean isFinished() { return finished; }

    protected void end() {Robot.arm.armStop();}

    protected void interrupted() { end(); }
}
