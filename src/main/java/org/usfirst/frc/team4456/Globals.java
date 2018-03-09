package org.usfirst.frc.team4456;

public class Globals {
    public static double[] armPositions = {0, 0, 0, 2000, 2000, 3000, 3000};
    public static double armP = 0.1;
    public static double armI = 0;
    public static double armD = 0;
    public static double armF = 0;
    public static int armSoftForwardLimit = 3000;
    public static int armSoftReverseLimit = 0;
    public static double armRampRate = .25;

    public static double[] wristPositions = {0, 1000, 2000, 2000, -600, -600, 0};
    public static double wristP = 0.1;
    public static double wristI = 0;
    public static double wristD = 0;
    public static double wristF = 0;
    public static int wristSoftForwardLimit = 3000;
    public static int wristSoftReverseLimit = 0;
    public static double wristRampRate = .25;

    public static int positionIndex;
}
