
package org.usfirst.frc.team78.robot;

import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Claw;
import org.usfirst.frc.team78.robot.subsystems.Elevator;
import org.usfirst.frc.team78.robot.subsystems.Vision;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
	
	public static OI oi;
	

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {	
		oi = new OI();
        // instantiate the command used for the autonomous period
		
        //****autonomousCommand = new ExampleCommand();****//TODO auto cmd here
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
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
    	SmartDashboard.putNumber("Gyro", Robot.chassis.getGyro()); //TODO Should be here?
    	SmartDashboard.putNumber("Elevator Encoder", Robot.elevator.getLiftEnc());
    	//SmartDashboard.putNumber("Stick Val", Robot.oi.getManipulatorLeftStick());
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}
