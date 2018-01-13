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
		aButton.toggleWhenPressed(new toggleIntake());
		JoystickButton bButton = new JoystickButton(joystick, 2);
		bButton.toggleWhenPressed(new toggleWinchForward());
		JoystickButton xButton = new JoystickButton(joystick, 3);
		xButton.toggleWhenPressed(new toggleShooter());
		JoystickButton yButton = new JoystickButton(joystick, 4);
		yButton.toggleWhenPressed(new toggleWinchReverse());
		JoystickButton leftBumper = new JoystickButton(joystick, 5);
		//leftBumper.whileHeld(new lowerDeflector());
		JoystickButton rightBumper = new JoystickButton(joystick, 6);
		//rightBumper.whileHeld(new raiseDeflector());
		JoystickButton backButton = new JoystickButton(joystick, 7);
		//selectButton command configuration here
		JoystickButton startButton = new JoystickButton(joystick, 8);
		//startButton command configuration here
		JoystickButton leftStick = new JoystickButton(joystick, 9);
		//leftStick command configuration here
		JoystickButton rightStick = new JoystickButton(joystick, 10);
		//rightStick command configuration here
		
	}
	
}
