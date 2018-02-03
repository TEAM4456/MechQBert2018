package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import sun.awt.geom.AreaOp;

public class Arm extends Subsystem{

    protected void initDefaultCommand() { }

    public void armUp(){
        RobotMap.diagActTalon.set(ControlMode.Position, 1);
        RobotMap.vertActTalon.set(ControlMode.Position, 1);
    }

    public void armDown(){
        RobotMap.diagActTalon.set(ControlMode.Position, -1);
        RobotMap.vertActTalon.set(ControlMode.Position, -1);
    }

    public void armStop(){
        RobotMap.diagActTalon.set(ControlMode.Position, 0);
        RobotMap.vertActTalon.set(ControlMode.Position, 0);
    }
}
