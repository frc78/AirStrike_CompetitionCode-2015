
package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseClaw extends Command {

    public CloseClaw() {
    	requires(Robot.claw);
    	setTimeout(0.14);
    }

    protected void initialize() {
    	Robot.claw.setSpeed(-1);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	Robot.claw.stopClaw();
    }

    protected void interrupted() {
    	end();
    }
}
