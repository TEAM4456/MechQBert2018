package org.usfirst.frc.team4456.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;


public class Wrist extends Subsystem {

    protected void initDefaultCommand() { }

    public void wristUp() {RobotMap.wristTalon.set(ControlMode.PercentOutput, 1);}

    public void wristDown() {RobotMap.wristTalon.set(ControlMode.PercentOutput, -1);}

    public void wristStop() {RobotMap.wristTalon.set(ControlMode.PercentOutput, 0);}
}
