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
		
		JoystickButton bButton = new JoystickButton(joystick, 2);
		
		JoystickButton xButton = new JoystickButton(joystick, 3);
		
		JoystickButton yButton = new JoystickButton(joystick, 4);
		
		JoystickButton leftBumper = new JoystickButton(joystick, 5);
		
		JoystickButton rightBumper = new JoystickButton(joystick, 6);
		
		JoystickButton backButton = new JoystickButton(joystick, 7);
		
		JoystickButton startButton = new JoystickButton(joystick, 8);
		
		JoystickButton leftStick = new JoystickButton(joystick, 9);
		
		JoystickButton rightStick = new JoystickButton(joystick, 10);
		
	}
	
}
