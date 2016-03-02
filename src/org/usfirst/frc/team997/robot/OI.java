package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.CollectBall;
import org.usfirst.frc.team997.robot.commands.DriverTwoJoystickCommandPressed;
import org.usfirst.frc.team997.robot.commands.DriverTwoJoystickCommandReleased;
import org.usfirst.frc.team997.robot.commands.GathererToAngle;
import org.usfirst.frc.team997.robot.commands.Shoot;
import org.usfirst.frc.team997.robot.commands.ShooterToAngle;
import org.usfirst.frc.team997.robot.commands.SpinUpShooterToggle;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	//Controllers
	private final Controller driverOne;
	private Joystick driverTwo;
	// driverTwo     supernes
	// name		port
	private final int b		= 9;
	private final int z     = 8;
	private final int a		= 7;
	private final int r		= 6;
	private final int up	= 1;
	private final int right	= 2;
	private final int down	= 3;
	private final int left	= 4;

	//Buttons 
	private final Button shootAngleHighButton;
	private final Button shootAngleMediumButton;
	private final Button shootAngleLowButton;
	private final Button spinUpShooterButton;
	private final Button collectBallButton;
	private final Button shootButton;
	private final Button gatherArmGround, gatherArmRobot, gatherArmMid;
	private final Button toggleManualControlOfDriverTwoJoystick;
	//private final Button arm;
	//private final Button shifterButton;

	public OI() {
		//primary Driver Button/Controls 
		driverOne = new Controller(RobotMap.joystickPort);
		
		//arm = new JoystickButton(myController, 3);
		//arm.whenPressed(new Arm()); //DO NOT TRUST THE VALUES OF THE POSITION, IT'S A LIE!!!
		
		//retracts kicker, arm to low, roller arm rolls in, gathers
		collectBallButton = new JoystickButton(driverOne, 4 /* 4 is y */);
		collectBallButton.whenPressed(new CollectBall());
		
		//shifterButton = new JoystickButton(myController, 4); //don't need unless driver wants it! 
		//shifterButton.whenPressed(new ToggleShift()); 
		
		//Secondary Driver Buttons/Controls
		driverTwo = new Joystick(RobotMap.joystickPortTwo);
		shootButton = new JoystickButton(driverTwo, 1);
		shootButton.whenPressed(new Shoot());
		
		//spin or slow down shooter based on toggle button state
		spinUpShooterButton = new JoystickButton(driverTwo, 2);
		spinUpShooterButton.whenPressed(new SpinUpShooterToggle());
		SmartDashboard.putData("Spin Up shooter", spinUpShooterButton);
		
		//sets shooter angle to medium position
		shootAngleMediumButton = new JoystickButton (driverTwo, 3);
		shootAngleMediumButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddle));
		SmartDashboard.putData("Shooter To Middle", shootAngleMediumButton);
		
		//sets shooter angle to low position
		shootAngleLowButton = new JoystickButton(driverTwo, 4);
		shootAngleLowButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotGround));
		SmartDashboard.putData("Shooter to Ground", shootAngleLowButton);
		
		//sets shooter angle to high position
		shootAngleHighButton = new JoystickButton (driverTwo, 5);
		shootAngleHighButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot));
		SmartDashboard.putData("Shooter to Robot", shootAngleHighButton);
		
		//sets arm to before hits ground position
		gatherArmGround = new JoystickButton(driverOne, 5);
		gatherArmGround.whenPressed(new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround));
		
		//sets arm to collecting position
		gatherArmMid = new JoystickButton(driverOne, 5);
		gatherArmMid.whenPressed(new GathererToAngle(RobotMap.Voltages.collectArmPostion));
		
		//sets arm to before hits robot position
		gatherArmRobot = new JoystickButton(driverOne, 5);
		gatherArmRobot.whenPressed(new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot));
		
		toggleManualControlOfDriverTwoJoystick = new JoystickButton(driverTwo, z);
		toggleManualControlOfDriverTwoJoystick.whenPressed(new DriverTwoJoystickCommandPressed());
		toggleManualControlOfDriverTwoJoystick.whenReleased(new DriverTwoJoystickCommandReleased());
		
		//smart dashboard stuff for the shooter
		SmartDashboard.putData("Shooter Pivot Low", new ShooterToAngle(RobotMap.Voltages.shooterPivotGround));
		SmartDashboard.putData("Shooter Pivot Midpoint", new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddle));  
		SmartDashboard.putData("Shooter Pivot High", new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot));
		
		//smart dashboard stuff for gatherer arm
		SmartDashboard.putData("Gatherer Arm Low", new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround));
		SmartDashboard.putData("Gatherer Arm to Collect", new GathererToAngle(RobotMap.Voltages.collectArmPostion));
		SmartDashboard.putData("Gatherer Arm High", new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot));
		
	}

	public static double deadBand(double a) {
		//deadband for joystick output
		return deadBand(a, RobotMap.deadBandValue);
	}

	public static double deadBand(double a, double dead) {
		//deadband for joystick output
		if (Math.abs(a) > dead){
			return a;
		} else {
			return 0;
		}
	}
	
	public double leftY() {
		return -deadBand(driverOne.getLeftRawY());
	}
	
	public double rightY(){
		return -deadBand(driverOne.getRightRawY());
	}
	
	public void userInfo() {
		SmartDashboard.putNumber("leftstickY", driverOne.getLeftRawY());
		SmartDashboard.putNumber("rightstickY", driverOne.getRightRawY());
		SmartDashboard.putNumber("TriggerAxis", this.getRawTriggerAxis());
	}
	
	//gets the value of the triggers on the Primary Drivers triggers.
	public double getRawTriggerAxis(){
		return (driverOne.getTriggerRight() - driverOne.getTriggerLeft())/2;
	}

	//this is saying to find the angle of the right joystick
	public double rightX(){
		return -driverOne.getRightRawX();
	}
	
	public double userZ() {
		return (driverTwo.getZ() + 1.0)/2.0;
	}
}
