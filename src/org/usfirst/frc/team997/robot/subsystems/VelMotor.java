package org.usfirst.frc.team997.robot.subsystems;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VelMotor {
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
    	if (x < band && x > -band) {
    		return 0;
    	}
    	return x;
    }
    
    private TimerTask updateTask = new TimerTask() {
    	public void run() { update(); }
    };
    
    private void update() {
    	double error = deadband(desiredVelocity - encoder.getRate(), .05);
    	currentCurrent = max(currentCurrent + error * calibrationFactor, 1);
    }
    
    public double max(double a, double max) {
    	if (a > max) return max;
    	else if (a < -max) return -max;
    	else return a;
    }
    
    public void start() {
    	try {
    		new Timer().schedule(updateTask, 0, 5);
    	} catch (Exception e) {
    	}
    }
    
    public void setDesiredVelocity(double d) { desiredVelocity = d; }
}

