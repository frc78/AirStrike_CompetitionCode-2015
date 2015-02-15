package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

    public DriveWithJoysticks() {
    	requires(Robot.chassis);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.chassis.driveWithJoysticks();
    }

    protected boolean isFinished() {
        return false; //default command
    }

    protected void end() {
    	Robot.chassis.stopAllDrive();
    }

    protected void interrupted() {
    	end();
    }
}
