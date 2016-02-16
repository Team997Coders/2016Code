package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.GenericHID;

public class Controller extends Joystick {
	
	//creates Joystick named joy. "joy is named after the emotion that fills my heart when
	//watching Kirito-kun engage in action with his mighty sword" - Sebastian
	//YAAASSSSSS!!!!!
	
	public Joystick joy;

	public Controller(int port) {
		super(port);
		// TODO Auto-generated constructor stub
		
		//"i should have named it Kirito-kun" - Sebastian
		
		joy = new Joystick(port);
	}
	
	//getting all the axes of the joystick
	
	public double getLeftRawX(){
		return joy.getRawAxis(0);
	}
	
	public double getLeftRawY(){
		return joy.getRawAxis(1);
	}
	
	public double getRightRawX(){
		return joy.getRawAxis(4);
	}
	
	public double getRightRawY() {
		return joy.getRawAxis(5);
	}
	
	//gets the trigger axis which we have not implemented yet
	
	public double getRawTriggerAxis(){ 
		return joy.getRawAxis(3);
	}
	
	//
	
	public boolean getLB(){
		return joy.getRawButton(5);
	}
	
	public boolean getRB(){
		return joy.getRawButton(4);
	}
}
