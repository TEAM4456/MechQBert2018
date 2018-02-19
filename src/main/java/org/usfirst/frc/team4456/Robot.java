package org.usfirst.frc.team4456;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import org.usfirst.frc.team4456.commands.*;
import org.usfirst.frc.team4456.subsystems.*;


import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends TimedRobot {
	
	public static AutonomousManager autonomousManager;
	
	public static Controls controls;
	
	// Subsystem declarations here
	public static Drive drive;
	
	boolean enabledInitialized = false;
	
	Command autonomousCommand;
	SendableChooser<Command> autonomousChooser;
	
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
		
		/*
		RobotMap.leftDriveTalon1.config_kP(0, SmartDashboard.getNumber("Left P", 0), 0);
		RobotMap.leftDriveTalon1.config_kI(0, SmartDashboard.getNumber("Left I", 0), 0);
		RobotMap.leftDriveTalon1.config_kD(0, SmartDashboard.getNumber("Left D", 0), 0);
		RobotMap.leftDriveTalon1.config_kF(0, SmartDashboard.getNumber("Left F", 0), 0);
		RobotMap.rightDriveTalon1.config_kP(0, SmartDashboard.getNumber("Right P", 0), 0);
		RobotMap.rightDriveTalon1.config_kI(0, SmartDashboard.getNumber("Right I", 0), 0);
		RobotMap.rightDriveTalon1.config_kD(0, SmartDashboard.getNumber("Right D", 0), 0);
		RobotMap.rightDriveTalon1.config_kF(0, SmartDashboard.getNumber("Right F", 0), 0);
		*/
		
		//SmartDashboard.putNumber("Navx yaw", RobotMap.navx.getYaw());
		//SmartDashboard.putNumber("Navx x-displacement", RobotMap.navx.getDisplacementX());
		
		
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
		
		//autonomousCommand.cancel();
	}
	
	public void disabledPeriodic() {}
	
	public void autonomousInit() {
		//autonomousCommand = (Command)autonomousChooser.getSelected();
		//autonomousCommand.start();
	}
	
	public void autonomousPeriodic() {
		autonomousManager.run();
	}
	
	public void teleopInit() {
		//autonomousCommand.cancel();
	}
	
	public void teleopPeriodic() {
		drive.betterArcadeDrive(controls.joystick);
		autonomousManager.run(); // for testing
	}
	
	public void testInit() {}
	
	public void testPeriodic() {}
	
}


