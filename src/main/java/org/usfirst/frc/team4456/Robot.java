package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.autonomous.AutonomousHandler;
import org.usfirst.frc.team4456.autonomous.AutonomousManager;
import org.usfirst.frc.team4456.subsystems.*;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Timer; // QUICK BODGE FOR BASELINE AUTO
import com.ctre.phoenix.motorcontrol.ControlMode; // QUICK BODGE FOR BASELINE AUTO

public class Robot extends TimedRobot {
	
	/* TODO: FIX AUTONOMOUS STUFF WITH MATCHDATA IN AUTONOMOUSHANDLER */
	
	private final Timer autoTimer = new Timer(); // QUICK BODGE FOR BASELINE AUTO
	
	private AutonomousHandler autonomousHandler;
	private AutonomousManager autonomousManager;
	
	private SendableChooser<String> positionChooser;
	
	public static Controls controls;
	
	// Subsystem declarations here
	public static Arm arm;
	public static Wrist wrist;
	public static Drive drive;
	public static Winch winch;
	public static Claw claw;
	
	private boolean enabledInitialized = false;
	
	public void robotInit() {
		
		CameraServer.getInstance().startAutomaticCapture();
		
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
		
		positionChooser = new SendableChooser<>();
		positionChooser.addDefault("Middle", "middle");
		positionChooser.addObject("Left", "left");
		positionChooser.addObject("Right", "right");
		SmartDashboard.putData("Starting Position", positionChooser);
		
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
		
	}
	
	public void disabledInit() {
		enabledInitialized = false;
	}
	
	public void disabledPeriodic() {}
	
	public void autonomousInit() {
		autoTimer.reset(); // QUICK BODGE FOR BASELINE AUTO
		autoTimer.start(); // QUICK BODGE FOR BASELINE AUTO
		
		/*
		String gameData = DriverStation.getInstance().getGameSpecificMessage(); // should have data by init time
		String robotPos = positionChooser.getSelected();
		autonomousHandler.startCompetitionAuto(gameData, robotPos);
		*/
	}
	
	public void autonomousPeriodic() {
		//autonomousHandler.run();
		
		// QUICK BODGE FOR BASELINE AUTO IN CASE WE HAVE NO RECORDINGS
		if (autoTimer.get() < 5.0) { // disgusting
			RobotMap.leftDriveTalon1.set(ControlMode.Velocity, 750); // gross
			RobotMap.rightDriveTalon1.set(ControlMode.Velocity, 750); // ew
		} else  {
			RobotMap.leftDriveTalon1.set(ControlMode.PercentOutput, 0); // why
			RobotMap.rightDriveTalon1.set(ControlMode.PercentOutput, 0); // please no
			autoTimer.stop(); // just don't
		}
	}
	
	public void teleopInit() {}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		if (!autonomousHandler.isPlaybackRunning()) {
			drive.betterArcadeDrive(controls.joystick);
		}
		autonomousHandler.run();
		SmartDashboard.putNumber("vertActTalon pos", RobotMap.vertActTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("diagActTalon pos", RobotMap.diagActTalon.getSelectedSensorPosition(0));
		SmartDashboard.putNumber("wristTalon pos", RobotMap.wristTalon.getSelectedSensorPosition(0));
		SmartDashboard.putBoolean("Position 1", Globals.positionIndex == 0);
		SmartDashboard.putBoolean("Position 2", Globals.positionIndex == 1);
		SmartDashboard.putBoolean("Position 3", Globals.positionIndex == 2);
		SmartDashboard.putBoolean("Position 4", Globals.positionIndex == 3);
		SmartDashboard.putBoolean("Position 5", Globals.positionIndex == 4);
		SmartDashboard.putBoolean("Position 6", Globals.positionIndex == 5);
		SmartDashboard.putBoolean("Position 7", Globals.positionIndex == 6);
		RobotMap.wristTalon.config_kP(0, SmartDashboard.getNumber("Wrist P", 0), 0);
		RobotMap.wristTalon.config_kI(0, SmartDashboard.getNumber("Wrist I", 0), 0);
		RobotMap.wristTalon.config_kD(0, SmartDashboard.getNumber("Wrist D", 0), 0);
		RobotMap.wristTalon.config_kF(0, SmartDashboard.getNumber("Wrist F", 0), 0);
	}
	
	public void testInit() {}
	
	public void testPeriodic() {}
	
}
