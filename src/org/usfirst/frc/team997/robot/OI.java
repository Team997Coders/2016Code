package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.CollectBallToggle;
import org.usfirst.frc.team997.robot.commands.GathererToAngle;
import org.usfirst.frc.team997.robot.commands.KillGather;
import org.usfirst.frc.team997.robot.commands.LearnShift;
import org.usfirst.frc.team997.robot.commands.Shoot;
import org.usfirst.frc.team997.robot.commands.ShooterToAngle;
import org.usfirst.frc.team997.robot.commands.SpinUpShooterToggle;
import org.usfirst.frc.team997.robot.commands.SpitBall;
import org.usfirst.frc.team997.robot.commands.ToggleLearnMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
	//Controllers
	private final Controller driverOne;
	private Joystick driverTwo;
	
	//Buttons 
	private final Button shootAngleHighButton, shootAngleMiddleLowButton, 
	                     shootAngleMiddleHighButton, shootAngleLowButton;
	private final Button spinUpShooterButton;
	private final Button collectBallButton;
	private final Button shootButton, learnUpButton, learnDwnButton;
	private final Button spitBallButton;
	//private final Button arm;
	//private final Button shifterButton;

	public OI() {
		//primary Driver Button/Controls 
		driverOne = new Controller(RobotMap.joystickPort);
		
		//arm = new JoystickButton(myController, 3);
		//arm.whenPressed(new Arm()); //DO NOT TRUST THE VALUES OF THE POSITION, IT'S A LIE!!!
		
		//retracts kicker, arm to low, roller arm rolls in, gathers
		collectBallButton = new JoystickButton(driverOne, 4);
		collectBallButton.whenPressed(new CollectBallToggle());
		
		//kicks kicker, sets arm to high, shoots ball slow
		spitBallButton = new JoystickButton(driverOne, 1);//IS THIS A GOOD BUTTON TO BIND TO??? TEST
		spitBallButton.whenPressed(new SpitBall());
		SmartDashboard.putData("Spit Ball", spitBallButton);
		
		//shifterButton = new JoystickButton(myController, 4); //don't need unless driver wants it! 
		//shifterButton.whenPressed(new ToggleShift()); 

		//Secondary Driver Buttons/Controls
		driverTwo = new Joystick(RobotMap.joystickPortTwo);
		shootButton = new JoystickButton(driverTwo, 6);
		shootButton.whenPressed(new Shoot());
		
		//spin or slow down shooter based on toggle button state
		spinUpShooterButton = new JoystickButton(driverTwo, 8);
		spinUpShooterButton.whenPressed(new SpinUpShooterToggle());
		SmartDashboard.putData("Spin Up shooter", spinUpShooterButton);
		
		//sets shooter angle to medium position
		shootAngleMiddleLowButton = new JoystickButton (driverTwo, 2);
		shootAngleMiddleLowButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddleLow));
		//SmartDashboard.putData("Shooter To Middle", shootAngleMiddleLowButton);
		
		shootAngleMiddleHighButton = new JoystickButton(driverTwo, 4);
		shootAngleMiddleHighButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddleHigh));
		//SmartDashboard.putData("Shooter to Middle High", shootAngleMiddleHighButton);
		
		//sets shooter angle to low position
		shootAngleLowButton = new JoystickButton(driverTwo, 3);
		shootAngleLowButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotGround));
		//SmartDashboard.putData("Shooter to Ground", shootAngleLowButton);
		
		//sets shooter angle to high position
		shootAngleHighButton = new JoystickButton (driverTwo, 1);
		shootAngleHighButton.whenPressed(new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot));
		//SmartDashboard.putData("Shooter to Robot", shootAngleHighButton);
		
		//sets arm to before hits ground position
		//gatherArmGround = new JoystickButton(driverOne, 5);//NEED TO FIX ALL FIVES
		//gatherArmGround.whenPressed(new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround));
		
		//sets arm to collecting position
		//gatherArmMid = new JoystickButton(driverOne, 5);//NEED TO FIX ALL FIVES
		//gatherArmMid.whenPressed(new GathererToAngle(RobotMap.Voltages.collectArmPostion));
		
		//sets arm to before hits robot position
		//gatherArmRobot = new JoystickButton(driverOne, 5);//NEED TO FIX ALL FIVES
		//gatherArmRobot.whenPressed(new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot));

		
		//smart dashboard stuff for the shooter
		SmartDashboard.putData("Shooter Pivot Low", new ShooterToAngle(RobotMap.Voltages.shooterPivotGround));
		SmartDashboard.putData("Shooter Pivot Midpoint Low", new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddleLow));
		SmartDashboard.putData("Shooter Pivot Midpoint High", new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddleHigh));
		SmartDashboard.putData("Shooter Pivot High", new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot));
		
		//smart dashboard stuff for gatherer arm
		//SmartDashboard.putData("Gatherer Arm Low", new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround));
		//SmartDashboard.putData("Gatherer Arm to Collect", new GathererToAngle(RobotMap.Voltages.collectArmPostion));
		//SmartDashboard.putData("Gatherer Arm High", new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot));
		SmartDashboard.putData("Kill the gatherer", new KillGather());
		
		// Add in the learn mode to reset mid shooter heights
		SmartDashboard.putBoolean("Learn Mode", RobotMap.learnMode);
		SmartDashboard.putData("Learn Mode Toggle", new ToggleLearnMode());
		learnUpButton = new JoystickButton(driverTwo, 5);
		learnUpButton.whenPressed(new LearnShift(true)); // true = up
		learnDwnButton = new JoystickButton(driverTwo, 7);
		learnDwnButton.whenPressed(new LearnShift(false));
		
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
	
	public int getPOV() {
		return driverTwo.getPOV();
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
