package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;

public class armDownOne extends Command {

    public armDownOne() { requires(Robot.arm); }

    protected void initialize() { Robot.arm.armDownOne(); }

    protected boolean isFinished() { return true; }

    protected void end() {}

    protected void interrupted() { end(); }
}
