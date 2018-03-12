package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {
	
	public Joystick joystick;

	public Controls() {
		
		joystick = new Joystick(0);
		
		/*
		FOR BUTTON CONFIGURATION:
			EXAMPLE SYNTAX: exampleButton.toggleWhenActive(new exampleCommand());
							exampleButton.whileHeld(new exampleCommand());
							exampleButton.whileActive(new exampleCommand());
							exampleButton.whenInactive(new exampleCommand());
							etc...
		 */


		JoystickButton aButton = new JoystickButton(joystick, 1);
		// TODO: PUT BACK
		aButton.whileHeld(new clawOpen());
		//aButton.whileHeld(new armVertRetract());
		
		JoystickButton bButton = new JoystickButton(joystick, 2);
		//bButton.whileHeld();
		//bButton.whileHeld(new armVertExtend());
		
		JoystickButton xButton = new JoystickButton(joystick, 3);
		// TODO: REMEMBER TO PUT BACK COMPETITION CONTROLS
		xButton.whileHeld(new wristBack());
		//xButton.whileHeld(new armDiagRetract());
		
		JoystickButton yButton = new JoystickButton(joystick, 4);
		// TODO: REMEMBER TO PUT BACK COMPETITION CONTROLS
		yButton.whileHeld(new wristOut());
		//yButton.whileHeld(new armDiagExtend());
		
		JoystickButton leftBumper = new JoystickButton(joystick, 5);
		// TODO: REMEMBER TO PUT BACK COMPETITION CONTROLS
		leftBumper.whenPressed(new armDownOne());
		//leftBumper.whileHeld(new armDown());
		
		JoystickButton rightBumper = new JoystickButton(joystick, 6);
		// TODO: REMEMBER TO PUT BACK COMPETITION CONTROLS
		rightBumper.whenPressed(new armUpOne());
		//rightBumper.whileHeld(new armUp());
		
		JoystickButton backButton = new JoystickButton(joystick, 7);
		backButton.whileHeld(new winchUp());
		
		//JoystickButton startButton = new JoystickButton(joystick, 8);
		//startButton.whileHeld();
		
		JoystickButton leftStick = new JoystickButton(joystick, 9);
		leftStick.whileHeld(new armVertRetract());
		
		JoystickButton rightStick = new JoystickButton(joystick, 10);
		rightStick.whileHeld(new armDiagRetract());
	}
	
}
