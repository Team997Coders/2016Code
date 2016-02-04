package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team997.robot.commands.ExampleCommand;
import org.usfirst.frc.team997.robot.commands.GatherIn;
import org.usfirst.frc.team997.robot.commands.GatherOut;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
	
	private final Controller myController;
	private final Button gatherIn;
	private final Button gatherOut;
	
	public OI() {
		
		myController = new Controller(RobotMap.JoystickPort);
		
		gatherIn = new JoystickButton(myController, 1);
		gatherIn.whenPressed(new GatherIn());
		
		gatherOut = new JoystickButton(myController, 2);
		gatherOut.whenPressed(new GatherOut());
	}

	private double deadband(double a) {
		
		//deadband for joystick output
		
		if (Math.abs(a)>.15){
			return a;
		} else {
			return 0;
		}
	}
	
	public double lefty() {
		return deadband(myController.getLeftRawY());
	}
	
	public double righty (){
		return deadband(myController.getRightRawX());
	}
	
	
	public void userinfo() {
		SmartDashboard.putNumber("leftstickY", myController.getLeftRawY());
		SmartDashboard.putNumber("rightstickY", myController.getRightRawY());
	}
	
	//// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

