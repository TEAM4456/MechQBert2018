package org.usfirst.frc.team4456;

public class Globals {
	
	// methinks that those drive PID values won't ever get re-tuned.
	// eh, might not need to anyways. -Amy
	
	public static double leftDriveP = 0.001; // MUST BE RE-TUNED FOR 2018 ROBOT
	public static double leftDriveI = 0.000001; // MUST BE RE-TUNED FOR 2018 ROBOT
	public static double leftDriveD = 1.0; // MUST BE RE-TUNED FOR 2018 ROBOT
	public static double leftDriveF = 0.3431872467; // MUST BE RE-TUNED FOR 2018 ROBOT
	
	public static double rightDriveP = 0.001; // MUST BE RE-TUNED FOR 2018 ROBOT
	public static double rightDriveI = 0.000001; // MUST BE RE-TUNED FOR 2018 ROBOT
	public static double rightDriveD = 1.0; // MUST BE RE-TUNED FOR 2018 ROBOT
	public static double rightDriveF = 0.3439511004; // MUST BE RE-TUNED FOR 2018 ROBOT
	
	public static double armP = 0.4;
	public static double armI = 0;
	public static double armD = 0;
	public static double armF = 0;
	public static double armRampRate = .2;
	
	public static double wristP = 0.6;
	public static double wristI = 0;
	public static double wristD = 0;
	public static double wristF = 0;
	public static double wristRampRate = .2;
	
	public static int positionIndex = 0;
	
	public static double[] armPositions = {0, 0, 0, 0, 13091, 13091, 15092};
	public static double[] wristPositions = {0, 2446, 969, 50, 50, 3495, 2748};
	
}
