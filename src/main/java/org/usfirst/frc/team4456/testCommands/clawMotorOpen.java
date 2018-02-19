package org.usfirst.frc.team4456.testCommands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class clawMotorOpen extends Command {
    public clawMotorOpen() { requires(Robot.claw); }

    protected void initialize() {
        Robot.claw.clawOpen();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.claw.clawStop();}

    protected void interrupted() { end(); }
}
