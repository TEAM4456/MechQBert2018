package org.usfirst.frc.team4456.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
    public static double[] armPositions = {0, 1000, 2000, 3000};
    int armPositionIndex = 0;

    protected void initDefaultCommand() {
        RobotMap.vertActTalon.configClosedloopRamp(.25,0);
        setPIDF(0.1, 0, 0, 0);
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


	public void armUpOne(){
	    if (armPositionIndex == armPositions.length - 1){
	        return;
        } else {
	        armPositionIndex++;
        }
        moveToPosition(armPositions[armPositionIndex]);
    }

    public void armDownOne(){
        if (armPositionIndex == 0){
            return;
        } else {
            armPositionIndex--;
        }
        moveToPosition(armPositions[armPositionIndex]);
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
