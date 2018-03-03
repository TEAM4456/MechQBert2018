package org.usfirst.frc.team4456.commands;

import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class wristOut extends Command{

    public wristOut() {
        requires(Robot.wrist);
        //requires(Robot.arm);
    }

    protected void initialize() {
        /*
        double [] x = Robot.arm.getArmPosition();
        double y = x[0];
        if(y <= 512)
            Robot.wrist.move(10.0);
        else
            Robot.wrist.move(20.0);
            */
        Robot.wrist.wristDown();
    }



    protected boolean isFinished() { return false; }

    protected void end() {Robot.wrist.wristStop();}

    protected void interrupted() { end(); }

}
