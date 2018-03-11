package org.usfirst.frc.team4456;

public class Globals {
    
    public static double armP = 0.4;
    public static double armI = 0;
    public static double armD = 0;
    public static double armF = 0;
    public static double armRampRate = .1;

    public static double wristP = 0.8;
    public static double wristI = 0;
    public static double wristD = 0;
    public static double wristF = 0;
    public static double wristRampRate = .1;

    public static int positionIndex = 0;
    
    public static double[] armVertPositions = { 0, 0,    0,   0,  6499,  6499,  10568 };
    public static double[] armDiagPositions = { 0, 0,    0,   0,  10891, 10891, 10293 };
    public static double[] wristPositions =   { 0, 1035, 510, 10, 0,     1341,  1191  };
    
}
