package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
/**
 *
 */
public class DriveStraightDistance extends Command {
	
	int m_distance_clicks;

    public DriveStraightDistance(double feet) {
    	requires(Robot.chassis);
    	
    	m_distance_clicks = -(int)(685*feet);
    	
    	setTimeout(feet/2);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetSensorData();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.driveStraightDistance(m_distance_clicks);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
