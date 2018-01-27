package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem{
    public final WPI_TalonSRX vertActTalon = RobotMap.vertActTalon;
    public final WPI_TalonSRX diagActTalon = RobotMap.diagActTalon;

    protected void initDefaultCommand() {

    }
}
