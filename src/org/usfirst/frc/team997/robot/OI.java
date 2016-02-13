package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team997.robot.commands.CollectBall;
import org.usfirst.frc.team997.robot.commands.ShootReturn;
import org.usfirst.frc.team997.robot.commands.ShooterAngleHigh;
import org.usfirst.frc.team997.robot.commands.ToggleShift;
import org.usfirst.frc.team997.robot.commands.circleLightToggle;
import org.usfirst.frc.team997.robot.commands.Arm;
import org.usfirst.frc.team997.robot.commands.ExampleCommand;
import org.usfirst.frc.team997.robot.commands.GatherIn;
import org.usfirst.frc.team997.robot.commands.GatherOut;
import org.usfirst.frc.team997.robot.commands.shooterAngleLow;
import org.usfirst.frc.team997.robot.commands.shooterAngleMedium;
import org.usfirst.frc.team997.robot.commands.spinUpShooter;

public class OI {
	//controllers
	private final Controller myController;
	private Joystick driverTwo;
	
	//Buttons 
	private final Button shootAngleHighButton;
	private final Button shootAngleMediumButton;
	private final Button shootAngleLowButton;
	private final Button spinUpShooterButton;
	private final Button CollectBallbutton;
	private final Button shootReturnButton;
	private final Button arm;
	//private final Button shifterButton;
	
	
	
	public OI() {
		//primary Driver Button/Controls 
		myController = new Controller(RobotMap.joystickPort);
		
		CollectBallbutton = new JoystickButton(driverTwo, 5);
		CollectBallbutton.whenPressed(new CollectBall());
		
		arm = new JoystickButton(myController, 3);
		arm.whenPressed(new Arm()); //DO NOT TRUST THE VALUES OF THE POSITION, ITS A LIE!!!
		
		//shifterButton = new JoystickButton(myController, 4); //dont need unless driver wants it! 
		//shifterButton.whenPressed(new ToggleShift()); 
		
		//Secondary Driver Buttons/Controls
		driverTwo = new Joystick(RobotMap.joystickPortTwo);
		
		shootReturnButton = new JoystickButton(driverTwo, 1);
		shootReturnButton.whenPressed(new ShootReturn());
		
		spinUpShooterButton = new JoystickButton(driverTwo, 2);
		spinUpShooterButton.whenPressed(new spinUpShooter());
		
		shootAngleMediumButton = new JoystickButton (driverTwo, 3);
		shootAngleMediumButton.whenPressed(new shooterAngleMedium());
		
		shootAngleLowButton = new JoystickButton(driverTwo, 4);
		shootAngleLowButton.whenPressed(new shooterAngleLow());
		
		shootAngleHighButton = new JoystickButton (driverTwo, 5);
		shootAngleHighButton.whenPressed(new ShooterAngleHigh());
		
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
