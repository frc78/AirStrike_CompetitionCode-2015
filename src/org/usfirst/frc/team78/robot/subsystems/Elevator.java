

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
	DigitalInput zeroLimit = new DigitalInput(RobotMap.ZERO_LIMIT);



	//VARIABLES
	double liftP = 0.0009;//TODO tune
	double liftError;
	int LIFT_ERROR_THRESHOLD = 15;//TODO tune
	public int liftErrorNeutralizedCount;
	
	static final int UPPER_LIMIT = 4825;
	//static final int LOWER_LIMIT = -1;
	
	public boolean isLiftZeroed = false;
	
	final public int FLOOR_PICKUP = 50;
	final public int SLED_PICKUP = 1415;
	final public int ABOVE_SLED = 3225;
	final public int ABOVE_TOTE = 1425;
	final public int SCORING_PLATFORM = 405;
	final public int STEP0 = 600;
	final public int STEP1 = 1775;
	final public int CAN_3 = 2004;//TODO these are made up values
	final public int CAN_4 = 2005;
	final public int CAN_5 = 2006;
	

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LiftWithSticks());
    }
    
    
 //_____________________________________________________________________________________________
 //lift methods
    
    public void moveToHeight (double height){
    	
    	liftError = height-getLiftEnc();
    	
    	double speed = liftP * liftError;

    	if (speed < .1 && speed > 0){
    		speed = .1;
    	}
    	else if(speed > -.1 && speed < 0){
    		speed = -.1;
    	}
    	
    	if (Math.abs(liftError) < LIFT_ERROR_THRESHOLD){
    		liftErrorNeutralizedCount ++; //command will end once within threshold for some time
    	}	
    	else{
    		liftErrorNeutralizedCount = 0;
    	}
    	
    	if((getLiftEnc() > UPPER_LIMIT) && speed > 0){
    		speed = 0;
    	}
    	
    	setLiftSpeed(speed);
    	
	}//end moveToHeight
    
    
    public void setLiftSpeed(double speed){	
    	if(getZeroLimit() && speed < 0){
    		resetLiftEncoder();
    		isLiftZeroed = true;
    		speed = 0;
    		
    	}
    	
    	liftMotor.set(speed);
    	
    }//end setLiftSpeed
    
    public void stopLift(){
    	setLiftSpeed(0);
    }//end stop lift
    
    
    public void liftWithSticks(){
    	double stick = Robot.oi.getManipulatorLeftStickY();
    	double speed;
    	
    	if(!Robot.oi.manipulatorStick.getRawButton(7)){//normal soft upper limit
	    	if((getLiftEnc() > UPPER_LIMIT) && stick > 0)
	    		speed = 0;
	    	else if(getLiftEnc() < 350 && stick < 0){
	    		speed = stick*.35;  //slow near bottom limit
	    	}
	    	else{
	    	speed = stick*0.85;
	    	}
    	}
    	else{//soft limit override mode
	    	speed = stick*0.85;
    	}

    	setLiftSpeed(speed);
    	
    	if(getLiftEnc() > 3100){
    		Robot.vision.turnOnIndicator();
    	}
    	else{
    		Robot.vision.turnOffIndicator();
    	}
    	
    } // end liftWithSticks()
    
    
    
//________________________________________________________________________________________________
//sensor methods
    public int getLiftEnc (){
    	return liftEnc.getRaw();
    }
    
    public boolean isOutOfBounds(){
    	return getLiftEnc() > UPPER_LIMIT || getZeroLimit();
    }
    public void resetElevatorNeutralizedCount(){
    	liftErrorNeutralizedCount = 0;
    }
    public void resetLiftEncoder(){
    	liftEnc.reset();
    }
    public boolean  getZeroLimit(){
    	return !zeroLimit.get();
    }

    
}//End elevator class

