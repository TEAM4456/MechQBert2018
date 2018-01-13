package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class autoMiddle extends CommandGroup {
	
	public autoMiddle() { addSequential(new autoDistance_VBus(4800, 0.4, true)); }
	
}
