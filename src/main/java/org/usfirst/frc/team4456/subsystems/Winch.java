package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	
	protected void initDefaultCommand() {}
	
	public void winchUp() { RobotMap.winchTalon1.set(ControlMode.PercentOutput, 1); }
	
	public void winchDown() { RobotMap.winchTalon1.set(ControlMode.PercentOutput, -1); }
	
	public void winchStop() { RobotMap.winchTalon1.set(ControlMode.PercentOutput, 0); }
	
}
