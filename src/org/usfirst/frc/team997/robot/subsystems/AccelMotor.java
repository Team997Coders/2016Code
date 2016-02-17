package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.commands.AccelCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AccelMotor extends Subsystem {
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

    /// updates the `accelCappedVel` member and calls VelMotor's setDesiredVelocity
    /// Controlls for a high acceleration on the robot.
    public void update() {
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
    	motor.update();
    }

    /// Sets the `desiredVel` member
    public void setDesiredVelocity(double d) { desiredVel = d; }

    /// Sets the `maxAccel` member
    public void setMaxAccel(double d) { maxAccel = d; }

    /// DOES NOT USE ACCELERATION CONTROL.  Used in AccelCommand's interrupted.
    public void stopMovingAbrupt() { motor.stopMovingAbrupt(); }
    
	protected void initDefaultCommand() {
		setDefaultCommand(new AccelCommand(this));
	}
	
	void smartDashboard() {
		SmartDashboard.putNumber("AccelMotor " + name + " maxAccel", maxAccel);
		SmartDashboard.putNumber("AccelMotor " + name + " desiredVel", desiredVel);
		SmartDashboard.putNumber("AccelMotor " + name + " accelCappedVel", accelCappedVel);
		this.motor.smartDashboard(name);
	}
}

