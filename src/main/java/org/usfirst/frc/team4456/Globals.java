package org.usfirst.frc.team4456;

import openrio.powerup.MatchData;

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
	public static double armRampRate = .3;
	
	public static double wristP = 0.6;
	public static double wristI = 0.0001;
	public static double wristD = 0;
	public static double wristF = 0;
	public static double wristRampRate = .2;
	
	public static int positionIndex = 0;
	
	public static double[] armPositions   = { 0,    0,    0,  0, 4522, 4522, 5096 };
	public static double[] wristPositions = { 0, 2600, 1051, 50,   50, 2951, 2748 };
	
	public static MatchData.OwnedSide switchSide = MatchData.getOwnedSide(MatchData.GameFeature.SWITCH_NEAR);
	public static MatchData.OwnedSide scaleSide = MatchData.getOwnedSide(MatchData.GameFeature.SCALE);
	
	public static boolean switchIsRight = false;
	public static boolean scaleIsRight = false;
	
	
}
