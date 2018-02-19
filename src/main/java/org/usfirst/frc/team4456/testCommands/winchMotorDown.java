package org.usfirst.frc.team4456.testCommands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.Robot;
import org.usfirst.frc.team4456.RobotMap;

public class winchMotorDown extends Command {
    public winchMotorDown() { requires(Robot.winch); }

    protected void initialize() {
        Robot.winch.winchDown();
    }

    protected boolean isFinished() { return true; }

    protected void end() {Robot.winch.winchStop();}

    protected void interrupted() { end(); }


}
