package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Hands extends Subsystem{
    public final WPI_TalonSRX clawTalon = RobotMap.clawTalon;
    public final WPI_TalonSRX wristTalon = RobotMap.wristTalon;

    protected void initDefaultCommand() {

    }
}
