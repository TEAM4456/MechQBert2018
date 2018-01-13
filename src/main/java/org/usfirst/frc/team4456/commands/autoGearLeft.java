package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoGearLeft extends CommandGroup {
	
	public autoGearLeft() {
		
		addSequential(new autoDistance_VBus(6700, 0.45, true));
		addSequential(new autoRotate_PID(30));
		addSequential(new autoDistance_VBus(8450, 0.45, true));
		
	}
	
}
