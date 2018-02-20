package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class wristMotorDown extends Command {
    public wristMotorDown() { requires(Robot.wrist); }

    protected void initialize() {
        Robot.wrist.wristDown();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }
}
