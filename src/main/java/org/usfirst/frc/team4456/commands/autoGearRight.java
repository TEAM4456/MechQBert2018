package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoGearRight extends CommandGroup {
	
	public autoGearRight() {
		
		addSequential(new autoDistance_VBus(6700, 0.45, true));
		addSequential(new autoRotate_PID(-32));
		addSequential(new autoDistance_VBus(8550, 0.45, true));
		
	}
	
}
