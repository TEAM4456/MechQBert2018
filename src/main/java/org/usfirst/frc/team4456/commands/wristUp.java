package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class wristUp extends Command  {

public wristUp() { requires(Robot.wrist); }

    protected void initialize() {
        Robot.wrist.wristUp();
    }

    protected boolean isFinished() { return false; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }

}
