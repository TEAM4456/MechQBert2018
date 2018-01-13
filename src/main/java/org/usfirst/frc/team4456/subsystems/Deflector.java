package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Deflector extends Subsystem {
	
	protected void initDefaultCommand() { /* set default command here */ }
	
	public static void raiseDeflector() {
		if (!RobotMap.deflectorSwitch.get()) { RobotMap.deflectorTalon.set(RobotMap.deflectorTalon.get() + 0.3); }
	}
	
	public static void lowerDeflector() { RobotMap.deflectorTalon.set(RobotMap.deflectorTalon.get() - 0.3); }
	
	public static void stopDeflector() { RobotMap.deflectorTalon.set(RobotMap.deflectorTalon.get()); }
	
}