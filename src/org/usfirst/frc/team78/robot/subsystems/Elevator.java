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


	//VARIABLES
	double liftP = 0.001;//TODO tune
	double liftError;
	int LIFT_ERROR_THRESHOLD = 10;//TODO tune
	int liftErrorNeutralizedCount;
	
	static final int UPPER_LIMIT = 4800;
	static final int LOWER_LIMIT = 250;
	

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
    	setLiftSpeed(speed);//TODO tune speed
    	
	}//end lift
    
    
    public void setLiftSpeed(double speed){	
    	liftMotor.set(speed);
    	
    }//end setLiftSpeed
    
    public void stopLift(){
    	setLiftSpeed(0);
    }//end stop lift
    
    
    public void liftWithSticks(){
    	double stick = Robot.oi.getManipulatorLeftStickY();
    	
    	if(!Robot.oi.manipulatorStick.getRawButton(7)){ //normal soft limits
	    	if((getLiftEnc() > UPPER_LIMIT) && stick > 0)
	    		setLiftSpeed(0);
	    	else if((getLiftEnc() < LOWER_LIMIT) && stick < 0)
	    		setLiftSpeed(0);
	    	else
	    	setLiftSpeed(.6*stick);
    	}
    	else{//soft limit override mode
	    	setLiftSpeed(.6*stick);
    	}
    } // end liftWithSticks()
    
    
    
//________________________________________________________________________________________________
//sensor methods
    public int getLiftEnc (){
    	return liftEnc.getRaw();
    }
    
    public boolean isOutOfBounds(){
    	return getLiftEnc() > UPPER_LIMIT || getLiftEnc() < LOWER_LIMIT;
    }

    
}//End elevator class

