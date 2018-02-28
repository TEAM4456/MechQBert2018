package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.autonomous.AutonomousHandler;
import org.usfirst.frc.team4456.autonomous.AutonomousManager;
import org.usfirst.frc.team4456.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
	
	public static AutonomousHandler autonomousHandler;
	public static AutonomousManager autonomousManager;
	
	public static Controls controls;
	
	// Subsystem declarations here
	public static Drive drive;
	
	boolean enabledInitialized = false;
	
	public void robotInit() {
		
		//CameraServer.getInstance().startAutomaticCapture();
		
		RobotMap.init();
		
		// construct subsystems here
		drive = new Drive();
		
		controls = new Controls();
		
		autonomousManager = new AutonomousManager(10, 100, new WPI_TalonSRX[] {
				RobotMap.leftDriveTalon1,
				RobotMap.rightDriveTalon1
		});
		autonomousHandler = new AutonomousHandler(autonomousManager, 8);
		
	}
	
	public void robotPeriodic() {
		/*
		SmartDashboard.putNumber("leftDriveTalon1", RobotMap.leftDriveTalon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightDriveTalon1", RobotMap.rightDriveTalon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Velocity", RobotMap.leftDriveTalon1.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Velocity", RobotMap.rightDriveTalon1.getSelectedSensorVelocity(0));
		*/
		
		// call custom enabled methods
		if (!enabledInitialized && isEnabled()) { enabledInit(); }
		if (isEnabled()) { enabledPeriodic(); }
	}
	
	// custom methods called by robotPeriodic()
	void enabledInit() {
		// init stuff upon enable here
		enabledInitialized = true;
	}
	void enabledPeriodic() {
		// run stuff periodically while enabled
		Scheduler.getInstance().run();
	}
	
	public void disabledInit() {
		enabledInitialized = false;
		autonomousHandler.onDisable();
	}
	
	public void disabledPeriodic() {}
	
	public void autonomousInit() {
	
	}
	
	public void autonomousPeriodic() {
		autonomousHandler.run();
	}
	
	public void teleopInit() {
	
	}
	
	public void teleopPeriodic() {
		drive.betterArcadeDrive(controls.joystick);
	}
	
	public void testInit() {
	
	}
	
	public void testPeriodic() {
		drive.betterArcadeDrive(controls.joystick);
		autonomousHandler.run();
	}
	
}


