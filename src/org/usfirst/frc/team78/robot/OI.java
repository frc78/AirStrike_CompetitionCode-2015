package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.DeployBurglar;
import org.usfirst.frc.team78.robot.commands.RetractBurglar;
import org.usfirst.frc.team78.robot.commands.StrafeTime;
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
	public Joystick manipulatorBackupStick;
	
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

	
	//MANIPULATOR BACKUP BUTTONS
	public Button btnOpenClawBackup;
	public Button btnCloseClawBackup;
	public Button btnKnockCanPreset;
	public Button btnTopPreset;

	
	public OI(){
		driverStick = new Joystick(0);
		manipulatorStick = new Joystick(1);
		manipulatorBackupStick = new Joystick(2);
		
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
		btnOpenClaw = new JoystickButton(manipulatorStick, RobotMap.openClawBtn);
		btnOpenClaw.whenPressed(new OpenClaw());
		btnOpenClaw.whenReleased(new LiftWithSticks());
		
		btnCloseClaw = new JoystickButton(manipulatorStick, RobotMap.closeClawBtn);
		btnCloseClaw.whenPressed(new CloseClaw());
		btnCloseClaw.whenReleased(new LiftWithSticks());
		
		btnFloorPickup = new JoystickButton(manipulatorStick, RobotMap.floorBtn);
		btnFloorPickup.whenPressed(new LiftMoveToHeight(Robot.elevator.FLOOR_PICKUP));
		btnFloorPickup.whenReleased(new LiftWithSticks());
		
		btnAboveTote = new JoystickButton(manipulatorStick, RobotMap.aboveToteBtn);
		btnAboveTote.whenPressed(new LiftMoveToHeight(Robot.elevator.ABOVE_TOTE));
		btnAboveTote.whenReleased(new LiftWithSticks());
		
		btnScoringPlatform = new JoystickButton(manipulatorStick, RobotMap.onToteBtn);
		btnScoringPlatform.whenPressed(new LiftMoveToHeight(Robot.elevator.SLED_PICKUP));
		btnScoringPlatform.whenReleased(new LiftWithSticks());
		
		btnStep = new JoystickButton(manipulatorStick, RobotMap.aboveToteBtn);
		btnStep.whenPressed(new LiftMoveToHeight(Robot.elevator.ABOVE_SLED));
		btnStep.whenReleased(new LiftWithSticks());
		
	
		//BACKUP BUTTONS
		/*btnOpenClawBackup = new JoystickButton(manipulatorBackupStick, RobotMap.openClawBackupBtn);
		btnOpenClawBackup.whenPressed(new OpenClaw());
		btnOpenClawBackup.whenReleased(new LiftWithSticks());
		
		btnCloseClawBackup = new JoystickButton(manipulatorBackupStick, RobotMap.closeClawBackupBtn);
		btnCloseClawBackup.whenPressed(new CloseClaw());
		btnCloseClawBackup.whenReleased(new LiftWithSticks());*/
		
		btnKnockCanPreset = new JoystickButton(manipulatorBackupStick, RobotMap.knockCanPresetBtn);
		btnKnockCanPreset.whenPressed(new LiftMoveToHeight(Robot.elevator.knockCanPreset));
		btnKnockCanPreset.whenReleased(new LiftWithSticks());
		
		btnTopPreset = new JoystickButton(manipulatorBackupStick, RobotMap.topPresetBtn);
		btnTopPreset.whenPressed(new LiftMoveToHeight(Robot.elevator.topPreset));
		btnTopPreset.whenReleased(new LiftWithSticks());
		
		
		
	//DASHBOARD COMMANDS
		SmartDashboard.putData(new ResetLiftEncoder());
		SmartDashboard.putData(new DeployBurglar());
		SmartDashboard.putData(new RetractBurglar());

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
	
	//MANIPULATOR BACKUP STICK
	public double getManipulatorBackupStick(){
		double stick = manipulatorBackupStick.getY();
		if(Math.abs(stick) < STICK_DEADZONE){
			return 0;
		}
		return -stick;
	}

}//end class

