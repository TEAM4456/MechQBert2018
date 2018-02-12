package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem{

    protected void initDefaultCommand() { }

    public void armUp(){
        RobotMap.vertActTalon.set(ControlMode.PercentOutput, .1);
    }

    public void armDown(){
        RobotMap.vertActTalon.set(ControlMode.PercentOutput, -.1);
    }

    public void armDiagExtend(){
        RobotMap.diagActTalon.set(ControlMode.PercentOutput, .1);
    }

    public void armDiagRetract(){
        RobotMap.diagActTalon.set(ControlMode.PercentOutput, -.1);

    }

    public void armVertStop(){
        RobotMap.vertActTalon.set(ControlMode.PercentOutput, 0);
    }

    public void armDiagStop(){
        RobotMap.diagActTalon.set(ControlMode.PercentOutput, 0);
    }
}
