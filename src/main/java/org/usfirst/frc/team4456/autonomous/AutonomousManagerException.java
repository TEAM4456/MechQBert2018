package org.usfirst.frc.team4456.autonomous;

public class AutonomousManagerException extends Exception {
	public AutonomousManagerException(String message) {
		super("AutonomousManager: " + message);
	}
}
