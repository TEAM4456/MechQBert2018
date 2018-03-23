package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Claw extends Subsystem {
	
	protected void initDefaultCommand() {}
	
	public void clawOpen() {
		RobotMap.clawTalon.set(ControlMode.PercentOutput, 1);
	}
	
	public void clawClose() {
		RobotMap.clawTalon.set(ControlMode.PercentOutput, -1);
	}
	
	
	public void clawStop() {
		RobotMap.clawTalon.set(ControlMode.PercentOutput, 0);
	}
	
	
}
