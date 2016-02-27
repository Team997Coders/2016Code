package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;

public class Controller extends Joystick {
	public Joystick joy;
	
	public Controller(int port) {
		super(port);		
		joy = new Joystick(port);
	}
	
	//getting all the axes of the joystick
	public double getLeftRawX() {
		return joy.getRawAxis(0);
	}
	
	public double getLeftRawY() {
		return joy.getRawAxis(1);
	}
	
	public double getRightRawX() {
		return joy.getRawAxis(4);
	}
	
	public double getRightRawY() {
		return joy.getRawAxis(5);
	}
	
	//gets the trigger axis
	public double getTriggerRight() { 
		return joy.getRawAxis(3);
	}
	
	public double getTriggerLeft() {
		return joy.getRawAxis(2);
	}
	
	public boolean getLB() {
		return joy.getRawButton(5);
	}
	
	public boolean getRB() {
		return joy.getRawButton(4);
	}
}
