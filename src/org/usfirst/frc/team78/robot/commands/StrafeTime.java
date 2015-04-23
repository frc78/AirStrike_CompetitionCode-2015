package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StrafeTime extends Command {

	double m_speed;
    public StrafeTime(double time, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.chassis);
        setTimeout(time);
        m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.chassis.resetSensorData();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.setStrafeSpeed(m_speed);
    	Robot.chassis.straightStrafeCorrection(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.stopAllDrive();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
