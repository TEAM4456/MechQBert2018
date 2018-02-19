package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class diagActDown extends Command {
    public diagActDown() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armDiagRetract();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.arm.armDiagStop();}

    protected void interrupted() { end(); }
}
