package org.usfirst.frc.team78.robot.subsystems;
import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Chassis extends Subsystem {
    
	//MOTORS
	Victor leftDrive1 = new Victor(RobotMap.LEFT_DRIVE_1);
	Victor leftDrive2 = new Victor(RobotMap.LEFT_DRIVE_2);
	Victor rightDrive1 = new Victor(RobotMap.RIGHT_DRIVE_1);
	Victor rightDrive2 = new Victor(RobotMap.RIGHT_DRIVE_2);
	Victor hDrive = new Victor(RobotMap.H_DRIVE);
	
	//SENSORS
	
	Gyro gyro = new Gyro(RobotMap.GYRO);
	Encoder rightEnc = new Encoder(RobotMap.RIGHT_ENC_A, RobotMap.RIGHT_ENC_B);
	Encoder leftEnc = new Encoder(RobotMap.LEFT_ENC_A, RobotMap.LEFT_ENC_B);
	BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();
	
	
	//VARIABLES
	
		//DISTANCE CALCULATION
	double distanceError;
	final double distanceP = 0.0003;
	final double DISTANCE_ERROR_THRESHOLD = 175;
	public int errorNeutralizedCount = 0;
	final double STRAIGHT_ERROR_CONST = (0.006);
	final double STRAIGHT_STRAFE_ERROR_CONST = (.055);
	

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
 //_____________________________________________________________________________________________
 //drive methods
    public void driveWithJoysticks(){
        double left = Robot.oi.getDriverLeftStick();
       	double right = Robot.oi.getDriverRightStick();
        	
       	if(Robot.oi.driverStick.getRawButton(7) && Robot.oi.driverStick.getRawButton(8)){
       		setSpeed(left, right);
       	}
       	else{
       	setSpeed(left*.5, right*.5);
       	}

       	
    }//end drive with joysticks
    

    
    public void setSpeed(double left, double right){
    	/*if(left > 0 && right > 0){
    		leftDrive1.set(-left*0.955);
    		leftDrive2.set(-left*0.955);
    		rightDrive1.set(right);
    		rightDrive1.set(right);
    	}
    	else if(left < 0 && right < 0){
    		leftDrive1.set(-left);
    		leftDrive2.set(-left);
    		rightDrive1.set(right*0.955);
    		rightDrive1.set(right*0.955);
    	}
    	else{*/
    		leftDrive1.set(-left);
    		leftDrive2.set(-left);
    		rightDrive1.set(right);
    		rightDrive2.set(right);
    	//}
    }//end set speed
    
    public void setStrafeSpeed(double speed){
    	hDrive.set(speed);
    }
   

    public void straightStrafeCorrection(double heading){
    	double driftError = heading - getGyro();
    	setSpeed(-((STRAIGHT_ERROR_CONST)*driftError), ((STRAIGHT_ERROR_CONST)*driftError));
    }
    
    public void stopAllDrive(){
    	setSpeed(0,0);
    	setStrafeSpeed(0);
    }
 
//_____________________________________________________________________________________________________________
//AUTO METHODS
    public void driveStraightDistance(double distance){
    	distanceError = distance - ((getLeftEnc()+getRightEnc())/2);//TODO why negated??
    	double speed = distanceP*(distanceError);

    	if (speed < .25 && speed > 0){
    		speed = .25;
    	}
    	
    	if(Math.abs(distanceError) < DISTANCE_ERROR_THRESHOLD){
    		errorNeutralizedCount ++;
    	}
    	else{
    		errorNeutralizedCount = 0;
    	}
    	
    	double driftError = getGyro();
    	setSpeed(speed+((STRAIGHT_STRAFE_ERROR_CONST)*driftError), speed-((STRAIGHT_STRAFE_ERROR_CONST)*driftError));
    }//end drive straight distance
    
//_____________________________________________________________________________________________________________
//SENSOR METHODS
    
    public double getGyro(){
    	return gyro.getAngle();
    }
    
    public double getLeftEnc(){
    	return -leftEnc.getRaw();//TODO negate??
    }
    
    public double getRightEnc(){
    	return rightEnc.getRaw();
    }
    
    public double getAccelX(){
    	return accelerometer.getX();
    }
    
    public double getAccelY(){
    	return accelerometer.getY();
    }
    
    public double getAccelZ(){
    	return accelerometer.getZ();
    }
    
    public void resetSensorData(){
    	gyro.reset();
    	leftEnc.reset();
    	rightEnc.reset();
    	errorNeutralizedCount = 0;
    }//end reset sensor data
    
} // end Chassis class

