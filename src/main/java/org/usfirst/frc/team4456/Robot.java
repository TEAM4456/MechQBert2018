package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.autonomous.AutonomousHandler;
import org.usfirst.frc.team4456.autonomous.AutonomousManager;
import org.usfirst.frc.team4456.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Robot extends IterativeRobot {
	
	private AutonomousHandler autonomousHandler;
	private AutonomousManager autonomousManager;
	
	public static Controls controls;
	
	// Subsystem declarations here
	public static Arm arm;
	public static Wrist wrist;
	public static Drive drive;
	public static Winch winch;
	public static Claw claw;
	
	private boolean enabledInitialized = false;
	
	public void robotInit() {
		
		//CameraServer.getInstance().startAutomaticCapture();
		
		// construct subsystems here
		RobotMap.init();
		
		drive = new Drive();
		arm = new Arm();
		wrist = new Wrist();
		winch = new Winch();
		claw = new Claw();
		
		controls = new Controls();
		
		autonomousManager = new AutonomousManager(20, 2, new WPI_TalonSRX[] {
				RobotMap.leftDriveTalon1,
				RobotMap.rightDriveTalon1
		});
		autonomousHandler = new AutonomousHandler(autonomousManager, 8);
		
	}
	
	public void robotPeriodic() {
		autonomousHandler.updateEnabledStatus(isEnabled());
		// call custom enabled methods
		if (!enabledInitialized && isEnabled()) { enabledInit(); }
		if (isEnabled()) { enabledPeriodic(); }
	}
	
	// custom methods called by robotPeriodic()
	void enabledInit() {
		// init stuff upon enable here
		arm.resetArmPosition();
		wrist.resetWristPosition();
		enabledInitialized = true;
	}
	void enabledPeriodic() {
		// run stuff periodically while enabled
		Scheduler.getInstance().run();
	}
	
	public void disabledInit() {
		enabledInitialized = false;
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
