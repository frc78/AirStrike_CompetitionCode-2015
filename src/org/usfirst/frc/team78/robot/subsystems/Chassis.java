package org.usfirst.frc.team78.robot.subsystems;

import org.usfirst.frc.team78.robot.Robot;
import org.usfirst.frc.team78.robot.RobotMap;
import org.usfirst.frc.team78.robot.commands.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	
	//VARIABLES
	double straightCorrectionConst = 0.006;
	double error;
	double distanceP = 0.0003;
	
	
	final double DISTANCE_ERROR_THRESHOLD = 175;
	public int errorNeutralizedCount = 0;
	double straightErrorConst = (0.006);

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveWithJoysticks());
    }
    
    
 //___________________________________________________________________________________________________________
 //drive methods
    public void driveWithJoysticks(){
        double left = Robot.oi.getDriverLeftStick();
       	double right = Robot.oi.getDriverRightStick();
        	
       	if(Robot.oi.driverStick.getRawButton(7) && Robot.oi.driverStick.getRawButton(8)){
       		setSpeed(left, right);
       	}
       	else{
       	setSpeed(left*.4, right*.4);
       	}
    }
    public void setSpeed(double left, double right){
    	if(left > 0 && right > 0){
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
    	else{
    		leftDrive1.set(-left);
    		leftDrive2.set(-left);
    		rightDrive1.set(right);
    		rightDrive2.set(right);
    	}
    }//end set speed
    
    public void stopDrive(){
    	setSpeed(0,0);
    }
 
//_____________________________________________________________________________________________________________
//AUTO METHODS
    public void driveStraightDistance(double distance){
    	error = distance - ((-getLeftEnc()-getRightEnc())/2);
    	double speed = distanceP*(error);
    	System.out.println("distance" + distance);
    	System.out.println("error" + error);

    	if (speed < .30 && speed > 0){
    		speed = .30;
    	}
    	

    	double driftError = getGyro();

    	if(Math.abs(error) < DISTANCE_ERROR_THRESHOLD){
    		errorNeutralizedCount ++;
    	}
    	else{
    		errorNeutralizedCount = 0;
    	}
    	setSpeed(speed+((straightErrorConst)*driftError), speed-((straightErrorConst)*driftError));
        
    	
    	
    }
    
//_____________________________________________________________________________________________________________
//SENSOR METHODS
    
    public double getGyro(){
    	return gyro.getAngle();
    }
    
    public double getLeftEnc(){
    	return leftEnc.getRaw();
    }
    public double getRightEnc(){
    	return -rightEnc.getRaw();
    }
    public void resetSensorData(){
    	gyro.reset();
    	leftEnc.reset();
    	rightEnc.reset();
    	errorNeutralizedCount = 0;
    }
}

