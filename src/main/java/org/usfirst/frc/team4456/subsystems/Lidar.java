package org.usfirst.frc.team4456.subsystems;

import org.usfirst.frc.team4456.RobotMap;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Lidar extends Subsystem {
	
	private String arduinoBuffer = "";
	private int arduinoBufferLength = 50;
	
	protected void initDefaultCommand() { /* set default command here */ }
	
	public double getDistance()
	{
		return average(format(arduinoBuffer));
	}
	
	public void update()
	{
		try
		{
			updateArduinoBuffer(RobotMap.lidarSerial.readString());
		}
		catch(Exception ex)
		{
			System.out.println("ERROR!: LIDAR READ_STRING\n" + ex);
		}
		//System.out.println(arduinoBuffer);
	}
	
	private double[] format(String string)
	{
		String[] stringArray_in = string.split("\n");
		String[] stringArray = java.util.Arrays.copyOfRange(stringArray_in, 1, stringArray_in.length - 1);
		double[] doubleArray = new double[stringArray.length];
		for(int i = 0; i < stringArray.length; i++)
		{
			try
			{
				doubleArray[i] = Double.parseDouble(stringArray[i]);
			}
			catch(Exception e)
			{
				doubleArray[i] = 1.0;
			}
		}
		
		return doubleArray;
	}
	
	private double average(double[] distances) {
		if (distances.length < 1) {
			return 0;
		}
		else {
			double sum = 0;
			
			for (double d : distances)
				sum += d;
			
			return sum / distances.length;
		}
		
	}
	
	private void updateArduinoBuffer(String newString)
	{
		String tempString = arduinoBuffer + newString;
		int len = Math.min(tempString.length(), arduinoBufferLength);
		arduinoBuffer = tempString.substring(tempString.length() - len);
	}
	
}
