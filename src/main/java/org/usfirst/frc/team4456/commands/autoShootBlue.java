package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoShootBlue extends CommandGroup {
	
	public autoShootBlue() {
		
		addSequential(new autoDistance_VBus(2800, 0.45, true));
		addSequential(new autoRotate_PID(25));
		addSequential(new autoDistance_VBus(1000, 0.4, false));
		addSequential(new autoRunShooterForTime(5));
		addSequential(new autoDistance_VBus(3500, 0.45, true));
		addSequential(new autoRotate_PID(-25));
		addSequential(new autoDistance_VBus(7000, 1.0, true));
		
	}
	
}
