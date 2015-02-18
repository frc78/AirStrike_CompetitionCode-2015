package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.RobotMap;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
	CameraServer server;
	Relay linedUpIndicator = new Relay(RobotMap.INDICATOR_LIGHT);

    public void initDefaultCommand() {
    	
    }
    
    public void initVision(){
    	server = CameraServer.getInstance();
    	server.setQuality(50);
    	server.startAutomaticCapture("cam0");
    }
    
    public void turnOnIndicator(){
    	linedUpIndicator.set(Value.kForward);
    }
    
    public void turnOffIndicator(){
    	linedUpIndicator.set(Value.kOff);
    }
}

