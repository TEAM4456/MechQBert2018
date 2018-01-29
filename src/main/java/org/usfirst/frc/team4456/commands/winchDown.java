package org.usfirst.frc.team4456.commands;

import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team4456.Robot;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team4456.subsystems.Winch;

public class winchDown extends Command{


    boolean finished;

    public winchDown() { requires(Robot.drive); }

    protected void initialize() { finished = false; }
    protected boolean isFinished() { return finished; }



}
