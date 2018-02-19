package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class winchMotorUp extends Command {


    public winchMotorUp() { requires(Robot.winch); }

    protected void initialize() {
        Robot.winch.winchUp();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.winch.winchStop();}

    protected void interrupted() { end(); }

}
