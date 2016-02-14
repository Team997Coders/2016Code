package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
class VelMotor {
    private SpeedController motor;
    private Encoder encoder;
    private double desiredVelocity, currentCurrent, calibrationFactor;
    
    public VelMotor(SpeedController motor, Encoder encoder, double calibrationFactor) {
    	this.motor = motor;
    	this.encoder = encoder;
    	this.calibrationFactor = calibrationFactor;
    	this.desiredVelocity = 0;
    }
    
    private double deadband(double x, double band) {
    	if (Math.abs(x) < band) {
    		return 0;
    	} else {
    		return x;
    	}
    }
    
    // called by AccelMotor's update (no visibility means only classes
    // in this package (subsystems) can call it).
    // gradually changes the actual velocity (controlling for velocity)
    void update() {
    	double error = deadband(desiredVelocity - encoder.getRate(), .05);
    	currentCurrent = max(currentCurrent + error * calibrationFactor, 1);
    	motor.set(deadband(currentCurrent, .05));
    }
    
    /// Maximum of two numbers.
    private double max(double a, double max) {
    	if (a > max) return max;
    	else if (a < -max) return -max;
    	else return a;
    }
    
    /// DOES NOT USE ACCELERATION CONTROL.  Will stop the motor it controlls (aka interrupted).
    void stopMovingAbrupt() {
    	motor.set(0);
    }

    public void setDesiredVelocity(double d) { desiredVelocity = d; }
}

