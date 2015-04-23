
package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.commands.AutoBurgle;
import org.usfirst.frc.team78.robot.commands.AutoDoNothing;
import org.usfirst.frc.team78.robot.commands.AutoDriveBackward;
import org.usfirst.frc.team78.robot.commands.AutoDriveForward;
import org.usfirst.frc.team78.robot.commands.AutoGrabRCForward;
import org.usfirst.frc.team78.robot.commands.AutoRCBackwards;
import org.usfirst.frc.team78.robot.commands.AutoRCStrafeScore;
import org.usfirst.frc.team78.robot.subsystems.Burglar;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Claw;
import org.usfirst.frc.team78.robot.subsystems.Elevator;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Chassis chassis = new Chassis();
	public static Elevator elevator = new Elevator();
	public static Claw claw = new Claw();
	public static Vision vision = new Vision();
	public static Burglar burglar = new Burglar();
	
	public static OI oi;
	
	SendableChooser autoChooser;

    Command autonomousCommand;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {	
		oi = new OI();
        // instantiate the command used for the autonomous period
		//autonomousCommand = new AutonomousCommand(); //robot builder made this last year, wpi doesn't recognize it now
		autoChooser = new SendableChooser();
		
		//AUTO MODES
		autoChooser.addDefault("Do Nothing", new AutoDoNothing());
		autoChooser.addObject("Burgle", new AutoBurgle());
		autoChooser.addObject("Drive Forward", new AutoDriveForward());
		autoChooser.addObject("Drive Backward", new AutoDriveBackward());
		//autoChooser.addObject("Grab RC Forward", new AutoGrabRCForward());
		//autoChooser.addObject("Grab RC Backwards", new AutoRCBackwards());
		
		//autoChooser.addObject("Grab RC Strafe", new AutoRCStrafeScore());
		
		SmartDashboard.putData("Auto Mode:", autoChooser);
		
        //****autonomousCommand = new ExampleCommand();****//auto cmd here. We declare it in autoinit right now, might now be right
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
    	 autonomousCommand = (Command) autoChooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
        
        burglar.initPump();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.elevator.isLiftZeroed = false;
        
        burglar.initPump();
    
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	SmartDashboard.putNumber("Gyro", Robot.chassis.getGyro()); 
    	SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getLiftEnc());
    	//SmartDashboard.putNumber("Stick Val", Robot.oi.getManipulatorLeftStick());
    	SmartDashboard.putBoolean("Lower Limit", Robot.elevator.getZeroLimit());
    	SmartDashboard.putNumber("Right Stick", Robot.oi.getDriverRightStick());
    	SmartDashboard.putNumber("Left Stick", Robot.oi.getDriverLeftStick());
    	SmartDashboard.putNumber("Pot", Robot.claw.getPot());
    	SmartDashboard.putBoolean("Is Lift Zeroed", Robot.elevator.isLiftZeroed);
    	SmartDashboard.putNumber("Right Side", Robot.chassis.getRightEnc());
    	SmartDashboard.putNumber("Left Side", Robot.chassis.getLeftEnc());
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
