package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
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
	private AnalogInput shooterSensor;
	private Servo shooterKickerFirst, shooterKickerSecond;

    public Shooter(int shootSensorPort, int shootMotorPort, int shootServoFirstPort, int shootServoSecondPort, int shootUpSource){
    	//shooterSensor detects when ball is fully in shooter grasp
    	shooterSensor = new AnalogInput(shootSensorPort);
    	
    	//shooterMotor is the VictorSP that controls the shooter wheels
    	shooterMotor = new VictorSP(shootMotorPort);
    	
    	//shooterServo is the Servo that controls the kicker
    	shooterKickerFirst = new Servo(shootServoFirstPort);
    	shooterKickerSecond = new Servo(shootServoSecondPort);
    	//makes UpSource. the thing
    	shooterCounter = new Counter();
    	shooterCounter.setUpSource(shootUpSource);
    	
    	//something involving units per time
    	shooterCounter.setDistancePerPulse(1);
    	
    	//shooterCounter counts the rotation of the wheels (using reflective tape)
    	shooterCounter = new Counter();	
    }
    
    private void setKickers(double d) {
    	shooterKickerFirst.set(d);
    	shooterKickerSecond.set(1 - d);
    }

    public void retractKicker(){
    	//moves kicker back to default position
    	shooterKickerSecond.set(RobotMap.kickerRightMax);
    	shooterKickerFirst.set(RobotMap.kickerLeftMax);
    }
    
    public void kickKicker(){    	
    	//kicks out kicker to kicking position
    	shooterKickerSecond.set(RobotMap.kickerRightMin);
    }
    
    public void speedUp(){
    	//causes shooterMotor (controlling the shooter wheels) to speed up
    	shooterMotor.set(-RobotMap.shooterShootingSpeed);
    }
    
    public void slowDown(){
    	//causes shooterMotor (controlling the shooter wheels) to slow down to stop
    	shooterMotor.set(0);
    }
    
    public void gatherBall(){
    	//gathers ball as long as the sensor is not activated
    	if (shooterSensor.getAverageVoltage() > 1){
    		shooterMotor.set(0);
    	} else {
    		shooterMotor.set(-RobotMap.shooterInSpeed);
    	}
    }
    
    public boolean getshooterSensor() {
    	return shooterSensor.getAverageVoltage() > 1;
	}
    
    public void smartDashboard() {
    	SmartDashboard.putNumber("Shooter Rate", shooterCounter.getRate());
    	SmartDashboard.putNumber("Average Speed of Shooter", shooterCounter.getSamplesToAverage());
		SmartDashboard.putNumber("Period", shooterCounter.getPeriod());
		SmartDashboard.putNumber("Shooter Sensor", shooterSensor.getAverageVoltage());
		SmartDashboard.putNumber("SENSOR SHOOTER VALUE", shooterSensor.getAccumulatorValue());
    }
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

