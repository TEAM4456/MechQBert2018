package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armDiagRetract extends Command {
    boolean finished;

    public armDiagRetract() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armDiagRetract();
    }

    protected boolean isFinished() { return finished; }

    protected void end() {Robot.arm.armDiagStop();}

    protected void interrupted() { end(); }
}
