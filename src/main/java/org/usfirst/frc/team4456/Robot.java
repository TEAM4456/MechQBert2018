package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.autonomous.AutonomousHandler;
import org.usfirst.frc.team4456.autonomous.AutonomousManager;
import org.usfirst.frc.team4456.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
		autonomousHandler = new AutonomousHandler(autonomousManager);
		
		// autonomous choosing stuff here
		
		
		/*autonomousCommand = new autoMiddle(); // default value, prevents null pointer exception
		
		autonomousChooser = new SendableChooser<Command>();
		autonomousChooser.addDefault("Auto Middle", new autoMiddle());

		autonomousChooser.addObject("Auto Gear Left", new autoGearLeft());
		autonomousChooser.addObject("Auto Gear Right (EXPERIMENTAL)", new autoGearRight());
		SmartDashboard.putData("Starting Position", autonomousChooser);*/
		
	}
	
	public void robotPeriodic() {
		
		SmartDashboard.putNumber("leftDriveTalon1", RobotMap.leftDriveTalon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightDriveTalon1", RobotMap.rightDriveTalon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("Left Velocity", RobotMap.leftDriveTalon1.getSelectedSensorVelocity(0));
		SmartDashboard.putNumber("Right Velocity", RobotMap.rightDriveTalon1.getSelectedSensorVelocity(0));
		
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
		autonomousHandler.run();
	}
	
	public void disabledInit() {
		enabledInitialized = false;
	}
	
	public void disabledPeriodic() {}
	
	public void autonomousInit() {
		/*
		try {
			autonomousManager.startRecording("test");
		} catch (AutonomousManagerException ex) {
			System.out.println(ex);
		}
		*/
	}
	
	public void autonomousPeriodic() {
		/*
		try {
			autonomousManager.run();
		} catch (AutonomousManagerException ex) {
			System.out.println(ex);
		}
		*/
	}
	
	public void teleopInit() {
		/*
		try {
			autonomousManager.startRecording("test");
		} catch (AutonomousManagerException ex) {
			System.out.println(ex);
		}
		*/
	}
	
	public void teleopPeriodic() {
		drive.betterArcadeDrive(controls.joystick);
	}
	
	public void testInit() {
	
	}
	
	public void testPeriodic() {
	
	}
	
}


