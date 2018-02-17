package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class wristOut extends Command{

    public wristOut() {
        requires(Robot.wrist);
        requires(Robot.arm);
    }

    protected void initialize() {
        if()
        Robot.wrist.move();
    }



    protected boolean isFinished() { return true; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }
}
