package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class wristBack extends Command {

    public wristBack() { requires(Robot.wrist); }

    protected void initialize() {
        Robot.wrist.move(0);
    }



    protected boolean isFinished() { return true; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }

}