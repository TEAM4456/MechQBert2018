package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Controls;
import org.usfirst.frc.team4456.Robot;

public class armUp extends Command {

    public armUp() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armUp();
    }

    protected boolean isFinished() { return Controls.bButton.get(); }

    protected void end() {Robot.arm.armVertStop();}

    protected void interrupted() { end(); }
}
