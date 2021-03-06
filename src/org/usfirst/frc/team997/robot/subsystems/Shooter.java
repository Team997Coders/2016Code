package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
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
	private AnalogInput shooterBallSensor; // ball sensor
	private Servo shooterKickerFirst, shooterKickerSecond;
	
	public static int globalSpinUp = 0;

    public Shooter(int shootSensorPort, int shootMotorPort, int shootServoFirstPort, int shootServoSecondPort){
    	//shooterSensor detects when ball is fully in shooter grasp
    	shooterBallSensor = new AnalogInput(shootSensorPort);
    	
    	//shooterMotor is the VictorSP that controls the shooter wheels
    	shooterMotor = new VictorSP(shootMotorPort);
    	
    	//shooterServo is the Servo that controls the kicker
    	shooterKickerFirst = new Servo(shootServoFirstPort);
    	shooterKickerSecond = new Servo(shootServoSecondPort);
    	
    	//Creates a counter object to track the speed of the wheels.
    	shooterCounter = new Counter();
    	//shooterCounter.setUpSource(shootUpSource);
    	shooterCounter.setDistancePerPulse(1);
    }

    public void retractKicker(){
    	//moves kicker back to default position
    	shooterKickerSecond.set(RobotMap.Voltages.kickerRightMax);
    	shooterKickerFirst.set(RobotMap.Voltages.kickerLeftMax);
    }
    
    public void kickKicker(){    	
    	//kicks out kicker to kicking position
    	shooterKickerSecond.set(RobotMap.Voltages.kickerRightMin);
    	shooterKickerFirst.set(RobotMap.Voltages.kickerLeftMin);
    }
    
    public void speedUp(){
    	//causes shooterMotor (controlling the shooter wheels) to speed up
    	shooterMotor.set(RobotMap.shooterShootingSpeed);
    }
    
    public void speedSlow(){
    	shooterMotor.set(RobotMap.shooterSlowSpeed);
    }
    
    public void stop(){
    	//causes shooterMotor (controlling the shooter wheels) to slow down to stop
    	shooterMotor.set(0);
    }
    
    public void gatherBall() {
    	//gathers ball as long as the sensor is not activated
    	if (false){//isBallCollected()){
    		shooterMotor.set(0);
    	} else {
    		shooterMotor.set(-RobotMap.shooterInSpeed);
    	}
    }
    
    public boolean isBallCollected() {
    	return false;//shooterBallSensor.getAverageVoltage() > 1;
	}
    
    public void smartDashboard() {
    	//SmartDashboard.putNumber("Shooter Rate", shooterCounter.getRate());
    	//SmartDashboard.putNumber("Average Speed of Shooter", shooterCounter.getSamplesToAverage());
		//SmartDashboard.putNumber("Period", shooterCounter.getPeriod());
		SmartDashboard.putNumber("Shooter Ball Distance Sensor", shooterBallSensor.getAverageVoltage());
		SmartDashboard.putBoolean("Shooter Ball Collected?", isBallCollected());
		//SmartDashboard.putNumber("SENSOR SHOOTER VALUE", shooterSensor.getAccumulatorValue());
		SmartDashboard.putNumber("Shooter Speed Value", (double) Shooter.globalSpinUp);
    }
	
    @Override
    public void initDefaultCommand() {}
}