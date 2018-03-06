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
		aButton.whileHeld(new armDown());
		JoystickButton bButton = new JoystickButton(joystick, 2);
		bButton.whileHeld(new armUp());
		JoystickButton xButton = new JoystickButton(joystick, 3);
		xButton.whenPressed(new armDownOne());
		JoystickButton yButton = new JoystickButton(joystick, 4);
		yButton.whenPressed(new armUpOne());
		JoystickButton leftBumper = new JoystickButton(joystick, 5);
		leftBumper.whenPressed(new wristBack());
		JoystickButton rightBumper = new JoystickButton(joystick, 6);
		rightBumper.whileHeld(new wristOut());
		JoystickButton backButton = new JoystickButton(joystick, 7);
		backButton.whileHeld(new winchUp());
		JoystickButton startButton = new JoystickButton(joystick, 8);
		//startButton.whileHeld(new winchDown());
		JoystickButton leftStick = new JoystickButton(joystick, 9);
		
		JoystickButton rightStick = new JoystickButton(joystick, 10);
		//rightStick.whileHeld(new clawOpen());
	}
	
}
