package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.autonomous.AutonomousHandler;
import org.usfirst.frc.team4456.autonomous.AutonomousManager;
import org.usfirst.frc.team4456.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends TimedRobot {
	
	private AutonomousHandler autonomousHandler;
	private AutonomousManager autonomousManager;
	
	public static Controls controls;
	
	// Subsystem declarations here
	public static Drive drive;
	
	private boolean enabledInitialized = false;
	
	public void robotInit() {
		
		//CameraServer.getInstance().startAutomaticCapture();
		
		RobotMap.init();
		
		// construct subsystems here
		drive = new Drive();
		
		controls = new Controls();
		
		autonomousManager = new AutonomousManager(20, 2, new WPI_TalonSRX[] {
				RobotMap.leftDriveTalon1,
				RobotMap.rightDriveTalon1
		});
		autonomousHandler = new AutonomousHandler(autonomousManager, 8);
		
	}
	
	public void robotPeriodic() {
		autonomousManager.updateEnabledStatus(isEnabled());
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
	
	public void autonomousInit() {}
	
	public void autonomousPeriodic() {
		autonomousHandler.run();
	}
	
	public void teleopInit() {}
	
	public void teleopPeriodic() {
		if (!autonomousHandler.isPlaybackRunning()) {
			drive.betterArcadeDrive(controls.joystick);
		}
		autonomousHandler.run();
	}
	
	public void testInit() {}
	
	public void testPeriodic() {}
	
}
