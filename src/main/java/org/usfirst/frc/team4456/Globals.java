package org.usfirst.frc.team4456;

public class Globals {
    
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
    
    public static double[] armVertPositions = { 0, 0,    0,    0,  8151, 8151, 10048 };
    public static double[] armDiagPositions = { 0, 0,    0,    0,  9669, 9669, 10570 };
    public static double[] wristPositions =   { 0, 2636, 1263, 53, 53,   3495, 2964  };
    
}
