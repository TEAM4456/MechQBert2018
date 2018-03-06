package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;
import org.usfirst.frc.team4456.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

    int armPositionIndex = 0;

    protected void initDefaultCommand() {
        RobotMap.vertActTalon.configClosedloopRamp(Constants.armRampRate,0);
        setPIDF(Constants.armP, Constants.armI, Constants.armD, Constants.armF);
        RobotMap.vertActTalon.configForwardSoftLimitThreshold(Constants.armSoftForwardLimit,10);
        RobotMap.vertActTalon.configForwardSoftLimitEnable(true, 10);
        RobotMap.vertActTalon.configReverseSoftLimitThreshold(Constants.armSoftReverseLimit,10);
        RobotMap.vertActTalon.configReverseSoftLimitEnable(true, 10);
        //RobotMap.diagActTalon.configClosedloopRamp(1,0);
    }

    private void setPIDF(double p, double i, double d, double f){
	    RobotMap.vertActTalon.config_kP(0, p,10);
        RobotMap.vertActTalon.config_kI(0, i,10);
        RobotMap.vertActTalon.config_kD(0, d,10);
        RobotMap.vertActTalon.config_kF(0, f,10);
    }

	public void moveToPosition(double target){
	    RobotMap.vertActTalon.set(ControlMode.Position, target);
        //RobotMap.diagActTalon.set(ControlMode.Position, target);
    }

    public void  resetArmPosition(){
        RobotMap.vertActTalon.setSelectedSensorPosition(0,0,10);
    }

	public void armUpOne(){
	    if (armPositionIndex == Constants.armPositions.length - 1){
	        return;
        } else {
	        armPositionIndex++;
        }
        moveToPosition(Constants.armPositions[armPositionIndex]);
    }

    public void armDownOne(){
        if (armPositionIndex == 0){
            return;
        } else {
            armPositionIndex--;
        }
        moveToPosition(Constants.armPositions[armPositionIndex]);
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


	public double[] getArmPosition() {
		double[] pos = {
				RobotMap.vertActTalon.getSensorCollection().getAnalogIn(),
				RobotMap.diagActTalon.getSensorCollection().getAnalogIn()
		};
		return pos;
	}
}
