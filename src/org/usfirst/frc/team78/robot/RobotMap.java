package org.usfirst.frc.team78.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
	
	//COLORS
	final static int YELLOW = 0;
	final static int BLUE = 1;
	final static int ORANGE = 2;
	final static int RED = 3;
	final static int DOUBLE_GREEN = 4;
	
	//MOTORS
	final static public int LEFT_DRIVE_1 = YELLOW;
	final static public int LEFT_DRIVE_2 = BLUE;
	final static public int RIGHT_DRIVE_1 = RED;
	final static public int RIGHT_DRIVE_2 = ORANGE;
	final static public int H_DRIVE = DOUBLE_GREEN;
	
	//ANALOG SENSORS
	final static public int GYRO = 0;
	
	//DIGITAL SENSORS
	final static public int LEFT_ENC_A = 0;
	final static public int LEFT_ENC_B = 1;
	final static public int RIGHT_ENC_A = 2;
	final static public int RIGHT_ENC_B = 3;
	
	
	
}
