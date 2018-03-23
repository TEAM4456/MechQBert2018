package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;
import org.usfirst.frc.team4456.Globals;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.ControlMode;

public class Wrist extends Subsystem {
	
	public Wrist() {
		RobotMap.wristTalon.configOpenloopRamp(Globals.wristRampRate, 0);
		RobotMap.wristTalon.configClosedloopRamp(Globals.wristRampRate, 0);
		setPIDF(Globals.wristP, Globals.wristI, Globals.wristD, Globals.wristF);
		SmartDashboard.putNumber("Wrist P", Globals.wristP);
		SmartDashboard.putNumber("Wrist I", Globals.wristI);
		SmartDashboard.putNumber("Wrist D", Globals.wristD);
		SmartDashboard.putNumber("Wrist F", Globals.wristF);
	}
	
	protected void initDefaultCommand() {}
	
	private void setPIDF(double p, double i, double d, double f) {
		RobotMap.wristTalon.config_kP(0, p, 10);
		RobotMap.wristTalon.config_kI(0, i, 10);
		RobotMap.wristTalon.config_kD(0, d, 10);
		RobotMap.wristTalon.config_kF(0, f, 10);
	}
	
	public void moveToPosition(double target) {
		RobotMap.wristTalon.set(ControlMode.Position, target);
	}
	
	public void wristUpOne() {
		moveToPosition(Globals.wristPositions[Globals.positionIndex]);
	}
	
	public void wristDownOne() {
		moveToPosition(Globals.wristPositions[Globals.positionIndex]);
	}
	
	public void resetWristPosition() {
		RobotMap.wristTalon.setSelectedSensorPosition(0, 0, 10);
	}
	
	public void wristUp() {
		RobotMap.wristTalon.set(ControlMode.PercentOutput, 1);
	}
	
	public void wristDown() {
		RobotMap.wristTalon.set(ControlMode.PercentOutput, -1);
	}
	
	public void wristStop() {
		RobotMap.wristTalon.set(ControlMode.PercentOutput, 0); /*Position,0 );*/
	}
	
	public void move(double targetPosition) {
		RobotMap.wristTalon.set(ControlMode.Position, targetPosition);
	}
	
}
