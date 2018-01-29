package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.commands.*;
import org.usfirst.frc.team4456.subsystems.*;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends IterativeRobot {
	
	public static Controls controls;
	
	// Subsystem declarations here


	public static Drive drive;


	
	boolean enabledInitialized = false;
	

	Command autonomousCommand;
	
	SendableChooser<Command> autonomousChooser;
	
	public void robotInit() {
		
		CameraServer.getInstance().startAutomaticCapture();
		
		RobotMap.init();
		
		// construct subsystems here


		drive = new Drive();


		
		controls = new Controls();
		

		
		// autonomous choosing stuff here
		
		/*autonomousCommand = new autoMiddle(); // default value, prevents null pointer exception
		
		autonomousChooser = new SendableChooser<Command>();
		autonomousChooser.addDefault("Auto Middle", new autoMiddle());

		autonomousChooser.addObject("Auto Gear Left", new autoGearLeft());
		autonomousChooser.addObject("Auto Gear Right (EXPERIMENTAL)", new autoGearRight());
		SmartDashboard.putData("Starting Position", autonomousChooser);*/
		
	}
	public void robotPeriodic() {
		/*
		if (RobotMap.lidarSerial != null) {
			lidar.update();
			SmartDashboard.putNumber("LiDAR Distance", lidar.getDistance());
		}
		*/


		SmartDashboard.putNumber("leftDriveTalon1", RobotMap.leftDriveTalon1.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("rightDriveTalon1", RobotMap.rightDriveTalon1.getSelectedSensorPosition(0));

		SmartDashboard.putNumber("Navx yaw", RobotMap.navx.getYaw());
		SmartDashboard.putNumber("Navx x-displacement", RobotMap.navx.getDisplacementX());
		
		// call custom enabled methods
		if (!enabledInitialized && isEnabled()) { enabledInit(); }
		if (isEnabled()) { enabledPeriodic(); }
	}
	
	// custom methods called by robotPeriodic()
	void enabledInit() {

		
		enabledInitialized = true;
	}
	void enabledPeriodic() { Scheduler.getInstance().run(); }
	
	public void disabledInit() {
		enabledInitialized = false;
		

		autonomousCommand.cancel();
	}
	public void disabledPeriodic() {}
	
	public void autonomousInit() {
		//autonomousCommand = (Command)autonomousChooser.getSelected();
		//autonomousCommand.start();
	}
	public void autonomousPeriodic() {}
	
	public void teleopInit() { autonomousCommand.cancel(); }
	public void teleopPeriodic() { drive.betterArcadeDrive(controls.joystick); }
	
	public void testInit() {}
	public void testPeriodic() {}
	
}

// "deplooy" -Andrew Rose, 2017, trying to type "deploy"
// pronounced "de-ploo-ee"
