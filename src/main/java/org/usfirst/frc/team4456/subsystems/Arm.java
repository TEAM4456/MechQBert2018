package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;
import org.usfirst.frc.team4456.Globals;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {



    protected void initDefaultCommand() {
        RobotMap.vertActTalon.configClosedloopRamp(Globals.armRampRate,0);
        setPIDF(Globals.armP, Globals.armI, Globals.armD, Globals.armF);
        RobotMap.vertActTalon.configForwardSoftLimitThreshold(Globals.armSoftForwardLimit,10);
        RobotMap.vertActTalon.configForwardSoftLimitEnable(true, 10);
        RobotMap.vertActTalon.configReverseSoftLimitThreshold(Globals.armSoftReverseLimit,10);
        RobotMap.vertActTalon.configReverseSoftLimitEnable(true, 10);
        RobotMap.diagActTalon.configClosedloopRamp(Globals.armRampRate,0);
        RobotMap.diagActTalon.configForwardSoftLimitThreshold(Globals.armSoftForwardLimit,10);
        RobotMap.diagActTalon.configForwardSoftLimitEnable(true, 10);
        RobotMap.diagActTalon.configReverseSoftLimitThreshold(Globals.armSoftReverseLimit,10);
        RobotMap.diagActTalon.configReverseSoftLimitEnable(true, 10);
    }

    private void setPIDF(double p, double i, double d, double f){
	    RobotMap.vertActTalon.config_kP(0, p,10);
        RobotMap.vertActTalon.config_kI(0, i,10);
        RobotMap.vertActTalon.config_kD(0, d,10);
        RobotMap.vertActTalon.config_kF(0, f,10);
        RobotMap.diagActTalon.config_kP(0, p,10);
        RobotMap.diagActTalon.config_kI(0, i,10);
        RobotMap.diagActTalon.config_kD(0, d,10);
        RobotMap.diagActTalon.config_kF(0, f,10);
    }

	public void moveToPosition(double target){
	    RobotMap.vertActTalon.set(ControlMode.Position, target);
        RobotMap.diagActTalon.set(ControlMode.Position, target);
    }

    public void  resetArmPosition(){
        RobotMap.vertActTalon.setSelectedSensorPosition(0,0,10);
    }

	public void armUpOne(){
	    if (Globals.positionIndex == Globals.armPositions.length - 1){
	        return;
        } else {
	        Globals.positionIndex++;
        }
        moveToPosition(Globals.armPositions[Globals.positionIndex]);
    }

    public void armDownOne(){
        if (Globals.positionIndex == 0){
            return;
        } else {
            Globals.positionIndex--;
        }
        moveToPosition(Globals.armPositions[Globals.positionIndex]);
    }

    public void armUp(){
        RobotMap.vertActTalon.set(ControlMode.PercentOutput, .5);
    }

    public void armDown(){
        RobotMap.vertActTalon.set(ControlMode.PercentOutput, -.5);
    }

    public void armDiagExtend() {
        RobotMap.diagActTalon.set(ControlMode.PercentOutput, .5);
    }

    public void armDiagRetract(){
        RobotMap.diagActTalon.set(ControlMode.PercentOutput, -.5);
    }

    public void armVertStop(){
        RobotMap.vertActTalon.set(ControlMode.PercentOutput, 0);
    }

    public void armDiagStop(){
        RobotMap.diagActTalon.set(ControlMode.PercentOutput, 0);
    }

}
