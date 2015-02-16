package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.CloseClaw;
import org.usfirst.frc.team78.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team78.robot.commands.IndicateLinedUp;
import org.usfirst.frc.team78.robot.commands.LiftMoveToHeight;
import org.usfirst.frc.team78.robot.commands.OpenClaw;
import org.usfirst.frc.team78.robot.commands.ResetLiftEncoder;
import org.usfirst.frc.team78.robot.commands.StopAllDrive;
import org.usfirst.frc.team78.robot.commands.StrafeLeft;
import org.usfirst.frc.team78.robot.commands.StrafeRight;

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
		btnStrafeLeft = new JoystickButton(driverStick, 5);
		btnStrafeLeft.whenPressed(new StrafeLeft());
		btnStrafeLeft.whenReleased(new StopAllDrive());
		
		btnStrafeRight = new JoystickButton(driverStick, 6);
		btnStrafeRight.whenPressed(new StrafeRight());
		btnStrafeRight.whenReleased(new StopAllDrive());
		
		btnIndicateLinedUp = new JoystickButton(driverStick, 1);
		btnIndicateLinedUp.whileHeld(new IndicateLinedUp());
		

	//MANIPULATOR BUTTONS
		btnOpenClaw = new JoystickButton(manipulatorStick, 9);
		btnOpenClaw.whenPressed(new OpenClaw());
		
		btnCloseClaw = new JoystickButton(manipulatorStick, 10);
		btnCloseClaw.whenPressed(new CloseClaw());
		
		btnFloorPickup = new JoystickButton(manipulatorStick, 2);
		btnFloorPickup.whenPressed(new LiftMoveToHeight(Robot.elevator.FLOOR_PICKUP));
		
		btnAboveTote = new JoystickButton(manipulatorStick, 1);
		btnAboveTote.whenPressed(new LiftMoveToHeight(Robot.elevator.ABOVE_TOTE));
		
		btnScoringPlatform = new JoystickButton(manipulatorStick, 3);
		btnScoringPlatform.whenPressed(new LiftMoveToHeight(Robot.elevator.SCORING_PLATFORM));
		
		btnStep = new JoystickButton(manipulatorStick, 4);
		btnStep.whenPressed(new LiftMoveToHeight(Robot.elevator.STEP0));
		
		btnCan3 = new JoystickButton(manipulatorStick, 5);
		btnCan3.whenPressed(new LiftMoveToHeight(Robot.elevator.CAN_3));
		
		btnCan4 = new JoystickButton(manipulatorStick, 6);
		btnCan4.whenPressed(new LiftMoveToHeight(Robot.elevator.CAN_4));
		
		btnCan5 = new JoystickButton(manipulatorStick, 8);
		btnCan5.whenPressed(new LiftMoveToHeight(Robot.elevator.CAN_5));
		
		
	//DASHBOARD COMMANDS
		SmartDashboard.putData(new DriveStraightDistance(8));
		SmartDashboard.putData(new ResetLiftEncoder());
	}
	
	
	
	//DRIVER STICK
	public double getDriverLeftStick() {
		return driverStick.getY();
	}
	
	public double getDriverRightStick(){
		return driverStick.getThrottle();
	}
	
	
	
	
	//MANIPULATOR STICK
	
	public double getManipulatorLeftStickY(){
		return -manipulatorStick.getY();
	}
	public double getManipulatorRightStickX(){
		return manipulatorStick.getTwist();
	}

}//end class

