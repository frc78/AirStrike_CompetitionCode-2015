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
    	
    	m_distance_clicks = (int)(
    			685*feet);
    	setTimeout(Math.abs(feet/2.5));
    }

    protected void initialize() {
    	Robot.chassis.resetSensorData();
    }

    protected void execute() {
    	Robot.chassis.driveStraightDistance(m_distance_clicks);
    }

    protected boolean isFinished() {
        return isTimedOut();//TODO better way?
    }

    protected void end() {
    	Robot.chassis.stopAllDrive();
    }

    protected void interrupted() {
    	end();
    }
}
