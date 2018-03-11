package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class wristBack extends Command {
    
    boolean finished = false;
    
    public wristBack() { requires(Robot.wrist); }

    protected void initialize() {
        Robot.wrist.wristUp();
        //Robot.wrist.move(0);
	    finished = true;
    }



    protected boolean isFinished() { return false; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }

}