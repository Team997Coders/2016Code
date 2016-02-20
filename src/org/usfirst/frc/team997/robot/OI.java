package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.CollectBall;
import org.usfirst.frc.team997.robot.commands.GathererToAngle;
import org.usfirst.frc.team997.robot.commands.Shoot;
import org.usfirst.frc.team997.robot.commands.ShooterToAngle;
import org.usfirst.frc.team997.robot.commands.SpinUpShooter;

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
	private final Button shootButton;
	private final Button gatherArmGround, gatherArmRobot, gatherArmMid;
	//private final Button arm;
	//private final Button shifterButton;
	
	
	
	public OI() {
		//primary Driver Button/Controls 
		myController = new Controller(RobotMap.joystickPort);
		
		//arm = new JoystickButton(myController, 3);
		//arm.whenPressed(new Arm()); //DO NOT TRUST THE VALUES OF THE POSITION, IT'S A LIE!!!
		
		collectBallButton = new JoystickButton(myController, 4);
		collectBallButton.whenPressed(new CollectBall());
		//shifterButton = new JoystickButton(myController, 4); //don't need unless driver wants it! 
		//shifterButton.whenPressed(new ToggleShift()); 
		
		//Secondary Driver Buttons/Controls
		driverTwo = new Joystick(RobotMap.joystickPortTwo);

		
		
		shootButton = new JoystickButton(driverTwo, 1);
		shootButton.whenPressed(new Shoot());
		
		spinUpShooterButton = new JoystickButton(driverTwo, 2);
		spinUpShooterButton.whenPressed(new SpinUpShooter());
		SmartDashboard.putData("Sin Up shooter", spinUpShooterButton);
		
		shootAngleMediumButton = new JoystickButton (driverTwo, 3);
		shootAngleMediumButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddle));
		SmartDashboard.putData("mid shooter low gather", shootAngleMediumButton);
		
		shootAngleLowButton = new JoystickButton(driverTwo, 4);
		shootAngleLowButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotGround));
		SmartDashboard.putData("low shooter high gather", shootAngleLowButton);
		
		shootAngleHighButton = new JoystickButton (driverTwo, 5);
		//shootAngleHighButton.whenPressed(new HighShooterLowGather());
		shootAngleHighButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot));
		SmartDashboard.putData("high shooter low gather", shootAngleHighButton);
		
		
		SmartDashboard.putData("Gatherer Arm Low", new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround));
		SmartDashboard.putData("Gatherer Arm to Collect", new GathererToAngle(RobotMap.Voltages.collectArmPostion));
		SmartDashboard.putData("Gatherer Arm High", new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot));

		gatherArmGround = new JoystickButton(myController, 5);
		gatherArmGround.whenPressed(new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround));
		gatherArmMid = new JoystickButton(myController, 5);
		gatherArmMid.whenPressed(new GathererToAngle(RobotMap.Voltages.collectArmPostion));
		gatherArmRobot = new JoystickButton(myController, 5);
		gatherArmRobot.whenPressed(new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot));
		
		SmartDashboard.putData("Shooter Pivot Low", new ShooterToAngle(RobotMap.Voltages.shooterPivotGround));
		SmartDashboard.putData("Shooter Pivot Midpoint", new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddle));  // nominal Shooting position
		SmartDashboard.putData("Shooter Pivot High", new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot));
	}

	public static double deadband(double a) {
		//deadband for joystick output
		
		return deadband(a, RobotMap.deadBandValue);
	}

	public static double deadband(double a, double dead) {
		//deadband for joystick output
		
		if (Math.abs(a) > dead){
			return a;
		} else {
			return 0;
		}
	}
	
	public double lefty() {
		return -deadband(myController.getLeftRawY());
	}
	
	public double righty(){
		return -deadband(myController.getRightRawY());
	}
	
	
	public void userinfo() {
		SmartDashboard.putNumber("leftstickY", myController.getLeftRawY());
		SmartDashboard.putNumber("rightstickY", myController.getRightRawY());
		SmartDashboard.putNumber("TriggerAxis", this.getRawTriggerAxis());
	}
	
	//gets the value of the triggers on the Primary Drivers triggers.
	public double getRawTriggerAxis(){
		return (myController.getTriggerRight() - myController.getTriggerLeft())/2;
	}

	//this is saying to find the fantastic  angle of the right joystick
	public double rightx(){
	
		return -myController.getRightRawX();
	}
	
	public double userz() {
		return (driverTwo.getZ() + 1.0)/2.0;
	}
}
