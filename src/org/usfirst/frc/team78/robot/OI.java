package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team78.robot.commands.LiftMoveToHeight;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick driverStick;
	public Joystick manipulatorStick;
	public Button btnLiftToZero;
	
	
	
	public OI(){
		driverStick = new Joystick(0);
		
		manipulatorStick = new Joystick(1);
		btnLiftToZero = new JoystickButton(manipulatorStick,2);
		btnLiftToZero.whenPressed(new LiftMoveToHeight(2000));
		
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
	
	public double getManipulatorLeftStickY(){
		return -manipulatorStick.getY();
	}//end get manipulator left stick
	public double getManipulatorRightStickX(){
		return manipulatorStick.getTwist();
	}

	

}

