package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	
	protected void initDefaultCommand() { /* set default command here */ }
	
	public static void startShooter() { RobotMap.shooterTalon.set(5.6); }
	
	public static void stopShooter() { RobotMap.shooterTalon.set(0); }
	
}