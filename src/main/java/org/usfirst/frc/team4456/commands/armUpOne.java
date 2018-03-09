package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armUpOne extends Command {

    public armUpOne() { requires(Robot.arm); }

    protected void initialize() {
        Robot.arm.armUpOne();
        Robot.wrist.wristUpOne();
    }

    protected boolean isFinished() { return true; }

    protected void end() {}

    protected void interrupted() { end(); }
}
