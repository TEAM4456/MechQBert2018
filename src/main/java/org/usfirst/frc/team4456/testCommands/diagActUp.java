package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class diagActUp extends Command {
    public diagActUp() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armDiagExtend();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.arm.armDiagStop();}

    protected void interrupted() { end(); }
}
