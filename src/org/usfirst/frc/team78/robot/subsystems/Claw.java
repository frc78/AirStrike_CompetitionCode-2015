package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.ClawWithStick;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
	
	Victor claw = new Victor(RobotMap.CLAW);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        setDefaultCommand(new ClawWithStick());
    }
    
    
    
    public void clawWithJoySticks(){
    	claw.set(Robot.oi.getManipulatorRightStickX());
    }
    
    public void stopClaw(){
    	claw.set(0);
    }
}

