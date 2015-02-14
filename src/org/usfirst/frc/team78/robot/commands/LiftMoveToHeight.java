package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftMoveToHeight extends Command {

	
	int m_height;
	int m_error;
	
    public LiftMoveToHeight(int height) {
    	requires(Robot.elevator);
    	m_height = height;
    	m_error = height - Robot.elevator.getLiftEnc();
    	setTimeout(Math.abs(m_error/500)); //TODO tune
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.elevator.resetElevatorNeutralizedCount();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevator.moveToHeight(m_height);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return (isTimedOut() || Robot.elevator.isOutOfBounds());
    	return Robot.elevator.liftErrorNeutralizedCount > 15;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.elevator.stopLift();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
