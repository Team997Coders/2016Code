package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.Button;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import org.usfirst.frc.team997.robot.commands.ExampleCommand;
//this is naming a controller mycontroller


public class OI {
private Controller myController;
//this is telling the code where to find the joystick 

public OI(){
	myController = new Controller (RobotMap.joystickPort);
}

public double righty(){
	return myController.getRightRawY();
}


//this is saying to find the fantastic angle of the left joystick
public double lefty(){
	return myController.getLeftRawY();
	
}

//this is saying to find the fantastic  angle of the right joystick
public double rightx(){
	return myController.getRightRawX();
}






















}

