package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;
import org.usfirst.frc.team4456.Globals;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Arm extends Subsystem {
	
	public Arm() {
		RobotMap.armTalon.configOpenloopRamp(Globals.armRampRate, 0);
		RobotMap.armTalon.configClosedloopRamp(Globals.armRampRate, 0);
		setPIDF(Globals.armP, Globals.armI, Globals.armD, Globals.armF);
		SmartDashboard.putNumber("Arm P", Globals.armP);
		SmartDashboard.putNumber("Arm I", Globals.armI);
		SmartDashboard.putNumber("Arm D", Globals.armD);
		SmartDashboard.putNumber("Arm F", Globals.armF);
	}
	
	protected void initDefaultCommand() {}
	
	private void setPIDF(double p, double i, double d, double f) {
		RobotMap.armTalon.config_kP(0, p, 10);
		RobotMap.armTalon.config_kI(0, i, 10);
		RobotMap.armTalon.config_kD(0, d, 10);
		RobotMap.armTalon.config_kF(0, f, 10);
	}
	
	public void moveArmToPosition(double vertTarget) {
		RobotMap.armTalon.set(ControlMode.Position, vertTarget);
	}
	
	public void resetArmPosition() {
		RobotMap.armTalon.setSelectedSensorPosition(0, 0, 10);
	}
	
	public void armUpOne() {
		if (Globals.positionIndex == Globals.armPositions.length - 1) {
			return;
		} else {
			Globals.positionIndex++;
		}
		moveArmToPosition(Globals.armPositions[Globals.positionIndex]);
	}
	
	public void armDownOne() {
		if (Globals.positionIndex == 0) {
			return;
		} else {
			Globals.positionIndex--;
		}
		moveArmToPosition(Globals.armPositions[Globals.positionIndex]);
	}
	
	public void armUp() {
		RobotMap.armTalon.set(ControlMode.PercentOutput, .6);
	}
	
	public void armDown() {
		RobotMap.armTalon.set(ControlMode.PercentOutput, -.6);
	}
	
	public void armStop() {
		RobotMap.armTalon.set(ControlMode.PercentOutput, 0);
	}
	
}
