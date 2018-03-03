package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armDiagExtend extends Command {


    public armDiagExtend() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armDiagExtend();
    }

    protected boolean isFinished() { return false; }

    protected void end() {Robot.arm.armDiagStop();}

    protected void interrupted() { end(); }

}
