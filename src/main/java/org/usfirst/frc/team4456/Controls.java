package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Controls {
	
	public Joystick joystick;
	public Joystick auxJoystick;
	
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
		aButton.whileHeld(new clawOpen());
		
		JoystickButton bButton = new JoystickButton(joystick, 2);
		bButton.whileHeld(new winchUp());
		
		JoystickButton xButton = new JoystickButton(joystick, 3);
		xButton.whileHeld(new wristBack());
		
		JoystickButton yButton = new JoystickButton(joystick, 4);
		yButton.whileHeld(new wristOut());
		
		JoystickButton leftBumper = new JoystickButton(joystick, 5);
		leftBumper.whenPressed(new armDownOne());
		
		JoystickButton rightBumper = new JoystickButton(joystick, 6);
		rightBumper.whenPressed(new armUpOne());
		
		JoystickButton backButton = new JoystickButton(joystick, 7);
		backButton.whileHeld(new winchDown());
		
		/* START BUTTON USED FOR AUTONOMOUSHANDLER */
		//JoystickButton startButton = new JoystickButton(joystick, 8);
		//startButton.whileHeld();
		
		JoystickButton leftStick = new JoystickButton(joystick, 9);
		leftStick.whileHeld(new armDown());
		
		JoystickButton rightStick = new JoystickButton(joystick, 10);
		rightStick.whileHeld(new armUp());
		
		
		/*
		auxJoystick = new Joystick(1);
		
		JoystickButton auxAButton = new JoystickButton(auxJoystick, 1);
		auxAButton.whileHeld(new clawOpen());
		
		JoystickButton auxBButton = new JoystickButton(auxJoystick, 2);
		auxBButton.whileHeld(new winchUp());
		
		JoystickButton auxXButton = new JoystickButton(auxJoystick, 3);
		auxXButton.whileHeld(new armDown());
		
		JoystickButton auxYButton = new JoystickButton(auxJoystick, 4);
		auxYButton.whileHeld(new armUp());
		
		JoystickButton auxLeftBumper = new JoystickButton(auxJoystick, 5);
		auxLeftBumper.whenPressed(new armDownOne());
		
		JoystickButton auxRightBumper = new JoystickButton(auxJoystick, 6);
		auxRightBumper.whenPressed(new armUpOne());
		
		JoystickButton auxBackButton = new JoystickButton(auxJoystick, 7);
		auxBackButton.whileHeld(new winchDown());
		
		JoystickButton auxStartButton = new JoystickButton(joystick, 8);
		//auxStartButton.whileHeld();
		
		JoystickButton auxLeftStick = new JoystickButton(auxJoystick, 9);
		auxLeftStick.whileHeld(new wristBack());
		
		JoystickButton auxRightStick = new JoystickButton(auxJoystick, 10);
		auxRightStick.whileHeld(new wristOut());
		*/
		
	}
	
}
