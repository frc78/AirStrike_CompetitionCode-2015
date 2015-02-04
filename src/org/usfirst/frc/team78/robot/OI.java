package org.usfirst.frc.team78.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team78.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick driverStick;
	
	
	
	
	public OI(){
		driverStick = new Joystick(0);
		SmartDashboard.putData(new DriveStraightDistance(8));
	}
	
	
	public double getDriverLeftStick() {
		return driverStick.getY();
	}
	public double getDriverRightStick(){
		return driverStick.getThrottle();
	}

}

