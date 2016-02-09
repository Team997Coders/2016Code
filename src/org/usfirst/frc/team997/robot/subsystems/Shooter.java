package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	private VictorSP shooterMotor;
	private Counter shooterCounter;
	private DigitalInput shooterSensor;
	private Servo shooterKicker;
    public Shooter(int shootSensorPort, int shootMotorPort, int shootServoPort, int shootUpSource){
    	//shooterSensor detects when ball is fully in shooter grasp
    	shooterSensor = new DigitalInput(shootSensorPort);
    	//shooterMotor is the VictorSP that controls the shooter wheels
    	shooterMotor = new VictorSP(shootMotorPort);
    	//shooterServo is the Servo that controls the kicker
    	shooterKicker = new Servo(shootServoPort);
    	//makes UpSource. the thing
    	shooterCounter.setUpSource(shootUpSource);
    	//something involving units per time
    	shooterCounter.setDistancePerPulse(1);
    	//shooterCounter counts the rotation of the wheels (using reflective tape)
    	shooterCounter = new Counter();	
    }
    
    public void retractKicker(){
    	//moves kicker back to default position (.8)
    	shooterKicker.set(.8);
    }
    
    public void kickKicker(){    	
    	//kicks out kicker to kicking position (.5)
    	shooterKicker.set(.5);
    }
    
    public void speedUp(){
    	//causes shooterMotor (controlling the shooter wheels) to speed up
    	shooterMotor.set(-.8);
    }
    
    public void slowDown(){
    	//causes shooterMotor (controlling the shooter wheels) to slow down to stop
    	shooterMotor.set(0);
    }
    
    public void gatherBall(){
    	//gathers ball as long as the sensor is not activated
    	if (shooterSensor.get()){
    		shooterMotor.set(0);
    	} else {
    		shooterMotor.set(.45);
    	}
    }
    
    public boolean getshooterSensor() {
    	//returns value of sensor (i'm guessing it's like 0 or 1 to indicate sensed or not?)
		return shooterSensor.get();
	}
    
    public void smartDashboard() {
    	SmartDashboard.putNumber("Shooter Rate", shooterCounter.getRate());
    	SmartDashboard.putNumber("Average Speed of Shooter", shooterCounter.getSamplesToAverage());
		SmartDashboard.putNumber("Period", shooterCounter.getPeriod());
    }
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

