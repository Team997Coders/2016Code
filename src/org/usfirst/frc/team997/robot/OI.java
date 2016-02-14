package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.Arm;
import org.usfirst.frc.team997.robot.commands.CollectBall;
import org.usfirst.frc.team997.robot.commands.HighShooterLowGather;
import org.usfirst.frc.team997.robot.commands.LowShooterHighGather;
import org.usfirst.frc.team997.robot.commands.MidShooterLowGather;
import org.usfirst.frc.team997.robot.commands.ShootReturn;
import org.usfirst.frc.team997.robot.commands.spinUpShooter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	//controllers
	private final Controller myController;
	private Joystick driverTwo;
	
	//Buttons 
	private final Button shootAngleHighButton;
	private final Button shootAngleMediumButton;
	private final Button shootAngleLowButton;
	private final Button spinUpShooterButton;
	private final Button collectBallButton;
	private final Button shootReturnButton;
	private final Button arm;
	//private final Button shifterButton;
	
	
	
	public OI() {
		//primary Driver Button/Controls 
		myController = new Controller(RobotMap.joystickPort);
		
		arm = new JoystickButton(myController, 3);
		arm.whenPressed(new Arm()); //DO NOT TRUST THE VALUES OF THE POSITION, IT'S A LIE!!!
		
		//shifterButton = new JoystickButton(myController, 4); //don't need unless driver wants it! 
		//shifterButton.whenPressed(new ToggleShift()); 
		
		//Secondary Driver Buttons/Controls
		driverTwo = new Joystick(RobotMap.joystickPortTwo);

		collectBallButton = new JoystickButton(driverTwo, 5);
		collectBallButton.whenPressed(new CollectBall());
		
		shootReturnButton = new JoystickButton(driverTwo, 1);
		shootReturnButton.whenPressed(new ShootReturn());
		
		spinUpShooterButton = new JoystickButton(driverTwo, 2);
		spinUpShooterButton.whenPressed(new spinUpShooter());
		
		shootAngleMediumButton = new JoystickButton (driverTwo, 3);
		shootAngleMediumButton.whenPressed(new MidShooterLowGather());
		
		shootAngleLowButton = new JoystickButton(driverTwo, 4);
		shootAngleLowButton.whenPressed(new LowShooterHighGather());
		
		shootAngleHighButton = new JoystickButton (driverTwo, 5);
		shootAngleHighButton.whenPressed(new HighShooterLowGather());
		
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
	
	public double righty(){
		return deadband(myController.getRightRawY());
	}
	
	
	public void userinfo() {
		SmartDashboard.putNumber("leftstickY", myController.getLeftRawY());
		SmartDashboard.putNumber("rightstickY", myController.getRightRawY());
	}
	
	//gets the value of the triggers on the Primary Drivers triggers.
	public double getGathererVoltage(){
		return myController.getRawTriggerAxis();
	}

	//this is saying to find the fantastic  angle of the right joystick
	public double rightx(){
	
		return myController.getRightRawX();
	}
}
