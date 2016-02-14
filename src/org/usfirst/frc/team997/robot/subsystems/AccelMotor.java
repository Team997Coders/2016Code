package org.usfirst.frc.team997.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 */
public class AccelMotor {
    private VelMotor motor;
    private double maxAccel, desiredVel, accelCappedVel;
    /// currently unused, would be used for smart dashboard
    private String name;
    
    public AccelMotor(VelMotor motor, double maxAcceleration, String name) {
    	this(motor, maxAcceleration);
    	this.name = name;
    }
    public AccelMotor(VelMotor motor, double maxAcceleration) {
    	this.motor = motor;
    	this.maxAccel = maxAcceleration;
    	this.desiredVel = 0;
    	this.accelCappedVel = 0;
    	this.motor.setDesiredVelocity(0);
    }

    public void start() {
    	try {
    		// every 5 milliseconds, run update()
    		new Timer().schedule(new TimerTask() { public void run() { update(); } },
    				             0, 5);
    	} catch (Exception e) {
    	}
    	motor.start();
    }

    /// updates the `accelCappedVel` member and calls VelMotor's setDesiredVelocity
    /// Controlls for a high acceleration on the robot.
    private void update() {
    	if (Math.abs(accelCappedVel - desiredVel) < maxAccel || maxAccel == 0) {
    		// if accelCappedVel is in the range (desiredVel - maxAccel, desiredVel + maxAccel) :
    		accelCappedVel = desiredVel;
    	} else if (desiredVel < accelCappedVel) {
    		// if accelCappedVel is in the range (desiredVel, inf)
    		accelCappedVel -= maxAccel;
    	} else if (desiredVel > accelCappedVel) {
    		// if accelCappedVel is in the range (-inf, desiredVel)
    		accelCappedVel += maxAccel;
    	}
    	// Assigns to the velocity controller.
    	motor.setDesiredVelocity(accelCappedVel);
    }

    /// Sets the `desiredVel` member
    public void setDesiredVelocity(double d) { desiredVel = d; }

    /// Sets the `maxAccel` member
    public void setMaxAccel(double d) { maxAccel = d; }
}

