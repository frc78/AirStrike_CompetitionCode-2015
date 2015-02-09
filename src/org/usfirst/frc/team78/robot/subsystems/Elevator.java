package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.LiftWithSticks;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Elevator extends Subsystem {
    
	//MOTORS
	Victor liftMotor = new Victor(RobotMap.ELEVATOR);
	
	//SENSORS
	Encoder liftEnc = new Encoder(RobotMap.LIFT_ENC_A, RobotMap.LIFT_ENC_B);
	DigitalInput upperLimit = new DigitalInput(RobotMap.LIFT_UPPER_LIMIT);
	DigitalInput lowerLimit = new DigitalInput(RobotMap.LIFT_LOWER_LIMIT);

	//VARIABLES
	double liftP = 0.0002;//TODO tune
	double liftError;
	int LIFT_ERROR_THRESHOLD = 10;//TODO tune
	int liftErrorNeutralizedCount;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LiftWithSticks());
    }
    
    
 //_____________________________________________________________________________________________
 //lift methods
    
    public void moveToHeight (double height){
    	
    	liftError = height-getLiftEnc();
    	
    	double speed = liftP*(liftError);

    	if (speed < .15 && speed > 0){
    		speed = .15;
    	}
    	else if(speed > -.15 && speed < 0){
    		speed = -.15;
    	}
    	
    	if(Math.abs(liftError) < LIFT_ERROR_THRESHOLD){
    		liftErrorNeutralizedCount ++;
    	}
    	else{
    		liftErrorNeutralizedCount = 0;
    	}
    	setLiftSpeed(speed*.2);//TODO tune speed
    	
	}//end lift
    
    
    public void setLiftSpeed(double speed){
    	liftMotor.set(speed);
    }//end setLiftSpeed
    
    public void stopLift(){
    	setLiftSpeed(0);
    }//end stop lift
    
    
    public void liftWithSticks(){
    	double stick = Robot.oi.getManipulatorLeftStick();
    	if(!Robot.oi.manipulatorStick.getRawButton(7)){ 
	    	if((getLiftEnc() > 4800 || getUpperLimitSwitch()) && stick < 0)
	    		setLiftSpeed(0);
	    	else if((getLiftEnc() < 300 || getLowerLimitSwitch()) && stick > 0)
	    		setLiftSpeed(0);
	    	else
	    	setLiftSpeed(.6*Robot.oi.getManipulatorLeftStick());
    	}
    	else{//soft limit override mode
    		if( getUpperLimitSwitch() && stick < 0)
	    		setLiftSpeed(0);
	    	else if(getLowerLimitSwitch() && stick > 0)
	    		setLiftSpeed(0);
	    	else
	    	setLiftSpeed(.6*Robot.oi.getManipulatorLeftStick());
    	}
    } // end liftWithSticks
    
//________________________________________________________________________________________________
//sensor methods
    public double getLiftEnc (){
    	return liftEnc.getRaw();//TODO negate?
    }
    public boolean getUpperLimitSwitch(){
    	return upperLimit.get();
    }
    public boolean getLowerLimitSwitch(){
    	return lowerLimit.get();
    }
    
}

