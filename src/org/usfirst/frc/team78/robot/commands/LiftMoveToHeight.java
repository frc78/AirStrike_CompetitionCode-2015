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
    	
    	setTimeout(Math.abs(2.5)); //TODO not linear
    }

    protected void initialize() {
    	Robot.elevator.resetElevatorNeutralizedCount();
    }

    protected void execute() {
    	Robot.elevator.moveToHeight(m_height);
    }

    protected boolean isFinished() {
        
    	return (Robot.elevator.liftErrorNeutralizedCount > 20 || isTimedOut()); //|| isTimedOut());//TODO NOT TESTED YET
    }

    protected void end() {
    	Robot.elevator.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
