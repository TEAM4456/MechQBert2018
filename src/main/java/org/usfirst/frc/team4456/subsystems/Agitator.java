package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Agitator extends Subsystem {
	
	protected void initDefaultCommand() { /* set default command here */ }
	
	public static void startAgitator() { RobotMap.agitatorTalon.set(2.5); }
	
	public static void stopAgitator() { RobotMap.agitatorTalon.set(0); }
	
}
