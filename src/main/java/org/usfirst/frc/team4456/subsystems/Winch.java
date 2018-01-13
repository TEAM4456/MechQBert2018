package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Winch extends Subsystem {
	
	protected void initDefaultCommand() { /* set default command here */ }
	
	public static void startWinchForward() { RobotMap.winchTalon.set(0.9); }
	
	public static void startWinchReverse() { RobotMap.winchTalon.set(-0.2); }
	
	public static void stopWinch() { RobotMap.winchTalon.set(0); }
	
}