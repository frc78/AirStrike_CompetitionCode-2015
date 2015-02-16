package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
	
	Victor claw = new Victor(RobotMap.CLAW);
	AnalogInput clawPot = new AnalogInput(RobotMap.CLAW_POT);
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//TODO default command
    }
    
    
    
    
    public void stopClaw(){
    	claw.set(0);
    }
    public void setSpeed(double speed){
    	claw.set(speed);
    }
}

