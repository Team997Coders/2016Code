package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team997.robot.commands.ToggleShift;
import org.usfirst.frc.team997.robot.commands.circleLightToggle;
import org.usfirst.frc.team997.robot.commands.Arm;
import org.usfirst.frc.team997.robot.commands.ExampleCommand;
import org.usfirst.frc.team997.robot.commands.GatherIn;
import org.usfirst.frc.team997.robot.commands.GatherOut;

public class OI {
	private final Controller myController;
	private final Button gatherIn;
	private final Button gatherOut;
	private final Button arm;
	private final Button shifterButton;
	private boolean toggleArm;
	private double armPos;
	
	public OI() {
		myController = new Controller(RobotMap.joystickPort);
		
		gatherIn = new JoystickButton(myController, 1);
		gatherIn.whenPressed(new GatherIn());
		
		gatherOut = new JoystickButton(myController, 2);
		gatherOut.whenPressed(new GatherOut());
		
		toggleArm = true;
		
		armPos = 0;
		
		if(toggleArm = true) {
			armPos = 43;
			toggleArm = false;
		} else if(toggleArm = false) {
			armPos = 0;
			toggleArm = true;
		}
		arm = new JoystickButton(myController, 3);
		arm.whenPressed(new Arm(Robot.oi.armPos)); //DO NOT TRUST THIS VALUE. IT IS ARBITRARY. IT LIES!!!!
		
		shifterButton = new JoystickButton(myController, 4); // FIX ME
		shifterButton.whenPressed(new ToggleShift());
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
		return deadband(myController.getRightRawX());
	}
	
	
	public void userinfo() {
		SmartDashboard.putNumber("leftstickY", myController.getLeftRawY());
		SmartDashboard.putNumber("rightstickY", myController.getRightRawY());
	}

	//this is saying to find the fantastic  angle of the right joystick
	public double rightx(){
		return myController.getRightRawX();
	}
}
