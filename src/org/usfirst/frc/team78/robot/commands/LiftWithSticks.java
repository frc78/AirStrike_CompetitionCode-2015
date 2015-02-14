package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LiftWithSticks extends Command {

    public LiftWithSticks() {
    	requires(Robot.elevator);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.elevator.liftWithSticks();
    }

    protected boolean isFinished() {
        return false; 
    }

    protected void end() {
    	Robot.elevator.stopLift();
    }

    protected void interrupted() {
    	end();
    }
}
