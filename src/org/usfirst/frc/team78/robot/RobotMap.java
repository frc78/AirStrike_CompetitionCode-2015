package org.usfirst.frc.team78.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {


	
	//MOTOR COLORS
	final static int YELLOW = 0;
	final static int BLUE = 1;
	final static int ORANGE = 2;
	final static int RED = 3;
	final static int DOUBLE_GREEN = 4;
	final static int DOUBLE_YELLOW = 5;
	final static int GREY = 6;
	
	//MOTORS
	final static public int LEFT_DRIVE_1 = YELLOW;
	final static public int LEFT_DRIVE_2 = BLUE;
	final static public int RIGHT_DRIVE_1 = RED;
	final static public int RIGHT_DRIVE_2 = ORANGE;
	final static public int H_DRIVE = DOUBLE_GREEN;
	final static public int ELEVATOR = DOUBLE_YELLOW;
	final static public int CLAW = GREY;
	
	//ANALOG SENSORS
	final static public int GYRO = 0;
	
	//DIGITAL SENSORS
	final static public int LEFT_ENC_A = 0;
	final static public int LEFT_ENC_B = 1;
	final static public int RIGHT_ENC_A = 2;
	final static public int RIGHT_ENC_B = 3;
	final static public int LIFT_ENC_A = 4;
	final static public int LIFT_ENC_B = 5;
	
	
	
	
}
