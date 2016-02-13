package org.usfirst.frc.team997.robot.subsystems;

import java.util.TimerTask;
import java.util.Timer;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AccelMotor {
    private VelMotor motor;
    private double maxAccel, desiredVel, accelCappedVel;
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
    		new Timer().schedule(updaterTask, 0, 5);
    	} catch (Exception e) {
    	}
    	motor.start();
    }
    private void update() {
    	if (Math.abs(accelCappedVel - desiredVel) < maxAccel || maxAccel == 0) {
    		accelCappedVel = desiredVel;
    	} else if (desiredVel < accelCappedVel) {
    		accelCappedVel -= maxAccel;
    	} else if (desiredVel > accelCappedVel) {
    		accelCappedVel += maxAccel;
    	}
    	motor.setDesiredVelocity(accelCappedVel);
    }
    public void setDesiredVelocity(double d) { desiredVel = d; }

    private TimerTask updaterTask = new TimerTask() {
    	public void run() { update(); }
    };

    public void setMaxAccel(double d) { maxAccel = d; }
}

