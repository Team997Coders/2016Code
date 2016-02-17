package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.OI;

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
    
    // called by AccelMotor's update (no visibility means only classes
    // in this package (subsystems) can call it).
    // gradually changes the actual velocity (controlling for velocity)
    void update() {
    	double error = OI.deadband(desiredVelocity - encoder.getRate(), .05);
    	currentCurrent = max(currentCurrent + error * calibrationFactor, 1);
    	motor.set(OI.deadband(currentCurrent, .05));
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

