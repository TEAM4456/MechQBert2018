package org.usfirst.frc.team4456.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import org.usfirst.frc.team4456.Globals;
import org.usfirst.frc.team4456.RobotMap;

import javax.naming.ldap.Control;

public class Wrist extends Subsystem {
	protected void initDefaultCommand() {
		RobotMap.wristTalon.configClosedloopRamp(Globals.wristRampRate,0);
		setPIDF(Globals.wristP, Globals.wristI, Globals.wristD, Globals.wristF);
		RobotMap.wristTalon.configForwardSoftLimitThreshold(Globals.wristSoftForwardLimit,10);
		RobotMap.wristTalon.configForwardSoftLimitEnable(true, 10);
		RobotMap.wristTalon.configReverseSoftLimitThreshold(Globals.wristSoftReverseLimit,10);
		RobotMap.wristTalon.configReverseSoftLimitEnable(true, 10);
	}

	private void setPIDF(double p, double i, double d, double f){
		RobotMap.wristTalon.config_kP(0, p,10);
		RobotMap.wristTalon.config_kI(0, i,10);
		RobotMap.wristTalon.config_kD(0, d,10);
		RobotMap.wristTalon.config_kF(0, f,10);
	}

	public void moveToPosition(double target){
		RobotMap.wristTalon.set(ControlMode.Position, target);
	}

	public void wristUpOne(){
		moveToPosition(Globals.wristPositions[Globals.positionIndex]);
	}

	public void wristDownOne(){
		moveToPosition(Globals.wristPositions[Globals.positionIndex]);
	}

	public void  resetWristPosition(){
		RobotMap.wristTalon.setSelectedSensorPosition(0,0,10);
	}
	
	public void wristUp() { RobotMap.wristTalon.set(ControlMode.PercentOutput, 1); }
	
	public void wristDown() { RobotMap.wristTalon.set(ControlMode.PercentOutput, -1); }
	
	public void wristStop() { RobotMap.wristTalon.set(ControlMode.PercentOutput, 0); /*Position,0 );*/ }
	
	public void move(double targetPosition) { RobotMap.wristTalon.set(ControlMode.Position, targetPosition); }
	
}
