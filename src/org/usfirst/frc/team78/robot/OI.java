package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.AutoStrafeTime;
import org.usfirst.frc.team78.robot.commands.CloseClaw;
import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team78.robot.commands.IndicateLinedUp;
import org.usfirst.frc.team78.robot.commands.LiftMoveToHeight;
import org.usfirst.frc.team78.robot.commands.LiftWithSticks;
import org.usfirst.frc.team78.robot.commands.OpenClaw;
import org.usfirst.frc.team78.robot.commands.ResetLiftEncoder;
import org.usfirst.frc.team78.robot.commands.StrafeLeft;
import org.usfirst.frc.team78.robot.commands.StrafeRight;
import org.usfirst.frc.team78.robot.commands.Turn;

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
	
	final static double STICK_DEADZONE = 0.05;
	
	//DRIVER BUTTONS
	public Button btnStrafeLeft;
	public Button btnStrafeRight;
	public Button btnIndicateLinedUp;
	
	//MANIPULATOR BUTTONS
	public Button btnOpenClaw;
	public Button btnCloseClaw;
	public Button btnMoveToMiddle;
	public Button btnFloorPickup;
	public Button btnAboveTote;
	public Button btnScoringPlatform;
	public Button btnStep;
	public Button btnCan3;
	public Button btnCan4;
	public Button btnCan5;

	
	public OI(){
		driverStick = new Joystick(0);
		manipulatorStick = new Joystick(1);
		
	//DRIVER BUTTONS
		btnIndicateLinedUp = new JoystickButton(driverStick, 2);
		btnIndicateLinedUp.whileHeld(new IndicateLinedUp());
		
		btnStrafeLeft = new JoystickButton(driverStick, 5);
		btnStrafeLeft.whenPressed(new StrafeLeft());
		//btnStrafeLeft.whenReleased(new StopAllDrive());
		
		btnStrafeRight = new JoystickButton(driverStick, 6);
		btnStrafeRight.whenPressed(new StrafeRight());
		//btnStrafeRight.whenReleased(new StopAllDrive());
		

		

	//MANIPULATOR BUTTONS
		btnOpenClaw = new JoystickButton(manipulatorStick, 9);
		btnOpenClaw.whenPressed(new OpenClaw());
		btnOpenClaw.whenReleased(new LiftWithSticks());
		
		btnCloseClaw = new JoystickButton(manipulatorStick, 10);
		btnCloseClaw.whenPressed(new CloseClaw());
		btnCloseClaw.whenReleased(new LiftWithSticks());
		
		btnFloorPickup = new JoystickButton(manipulatorStick, 2);
		btnFloorPickup.whenPressed(new LiftMoveToHeight(Robot.elevator.FLOOR_PICKUP));
		btnFloorPickup.whenReleased(new LiftWithSticks());
		
		btnAboveTote = new JoystickButton(manipulatorStick, 1);
		btnAboveTote.whenPressed(new LiftMoveToHeight(Robot.elevator.ABOVE_TOTE));
		btnAboveTote.whenReleased(new LiftWithSticks());
		
		btnScoringPlatform = new JoystickButton(manipulatorStick, 3);
		btnScoringPlatform.whenPressed(new LiftMoveToHeight(Robot.elevator.SLED_PICKUP));
		btnScoringPlatform.whenReleased(new LiftWithSticks());
		
		btnStep = new JoystickButton(manipulatorStick, 4);
		btnStep.whenPressed(new LiftMoveToHeight(Robot.elevator.ABOVE_SLED));
		btnStep.whenReleased(new LiftWithSticks());
		
		btnCan3 = new JoystickButton(manipulatorStick, 5);
		btnCan3.whenPressed(new LiftMoveToHeight(Robot.elevator.CAN_3));
		btnCan3.whenReleased(new LiftWithSticks());
		
		btnCan4 = new JoystickButton(manipulatorStick, 6);
		btnCan4.whenPressed(new LiftMoveToHeight(Robot.elevator.CAN_4));
		btnCan4.whenReleased(new LiftWithSticks());
		
		btnCan5 = new JoystickButton(manipulatorStick, 8);
		btnCan5.whenPressed(new LiftMoveToHeight(Robot.elevator.CAN_5));
		btnCan5.whenReleased(new LiftWithSticks());
		
		
	//DASHBOARD COMMANDS
		SmartDashboard.putData(new DriveStraightDistance(5));
		SmartDashboard.putData(new ResetLiftEncoder());
		SmartDashboard.putData(new Turn(-90));
		SmartDashboard.putData(new AutoStrafeTime(2, 0.5));//negative = left
	}
	
	
	
	//DRIVER STICK
	public double getDriverLeftStick() {
		double stick = driverStick.getY();
		if(Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}
		else 
			return stick;
	}
	
	public double getDriverRightStick(){
		double stick = driverStick.getThrottle();
		if(Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}
		return stick;
	}
	
	
	
	
	//MANIPULATOR STICK
	
	public double getManipulatorLeftStickY(){
		return -manipulatorStick.getY();
	}
	public double getManipulatorRightStickX(){
		return manipulatorStick.getTwist();
	}

}//end class

