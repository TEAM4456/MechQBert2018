package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class wristDown extends Command{

    public wristDown() { requires(Robot.wrist); }

    protected void initialize() {
        Robot.wrist.wristDown();
    }

    protected boolean isFinished() { return false; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }
}
