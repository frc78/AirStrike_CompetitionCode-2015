package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick driverStick;
	public Joystick manipulatorStick;
	
	
	
	public OI(){
		driverStick = new Joystick(0);
		manipulatorStick = new Joystick(1);
		SmartDashboard.putData(new DriveStraightDistance(8));
	}
	
	//DRIVER STICK
	public double getDriverLeftStick() {
		return driverStick.getY();
	}//end get driver left stick
	
	public double getDriverRightStick(){
		return driverStick.getThrottle();
	}//end get driver right stick
	
	
	
	
	//MANIPULATOR STICK
	public double getManipulatorLeftStick(){
		return manipulatorStick.getY();
	}//end get manipulator left stick

	

}

