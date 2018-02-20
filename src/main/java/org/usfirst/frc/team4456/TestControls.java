package org.usfirst.frc.team4456;

import org.usfirst.frc.team4456.testCommands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class TestControls {

    public Joystick joystick;

    public TestControls() {

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
        aButton.whileHeld(new vertActUp());
        JoystickButton bButton = new JoystickButton(joystick, 2);
        bButton.whileHeld(new vertActDown());
        JoystickButton xButton = new JoystickButton(joystick, 3);
        xButton.whileHeld(new diagActUp());
        JoystickButton yButton = new JoystickButton(joystick, 4);
        yButton.whileHeld(new diagActDown());
        JoystickButton leftBumper = new JoystickButton(joystick, 5);
        leftBumper.whileHeld(new wristMotorDown());
        JoystickButton rightBumper = new JoystickButton(joystick, 6);
        rightBumper.whileHeld(new wristMotorUp());
        JoystickButton backButton = new JoystickButton(joystick, 7);
        //selectButton command configuration here
        backButton.whileHeld(new winchMotorDown());
        JoystickButton startButton = new JoystickButton(joystick, 8);
        //startButton command configuration here
        startButton.whileHeld(new winchMotorUp());
        JoystickButton leftStick = new JoystickButton(joystick, 9);
        leftStick.whileHeld(new clawMotorOpen());
        JoystickButton rightStick = new JoystickButton(joystick, 10);
        //rightStick command configuration here

    }

}