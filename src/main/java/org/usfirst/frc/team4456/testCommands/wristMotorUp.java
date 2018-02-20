package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class wristMotorUp extends Command {
    public wristMotorUp() { requires(Robot.wrist); }

    protected void initialize() {
        Robot.wrist.wristUp();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }
}
