package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.command.Command;

public class RobotMap {
	static final int
	// NAME                            = PORT

	// PWM
	leftMotorPort                      = 0, // drive
	rightMotorPort                     = 1, // drive
	shooterMotorPort                   = 2, // shooter
	gatherArmMotorPort                 = 3, // gatherer arm
	rollerMotorPort                    = 4, // gatherer roller
	aimingMotorPort                    = 5, // aimer.  shooter lifter?
	servoMotorSecondPort               = 8, // ball pusher servo right
	servoMotorFirstPort                = 9, // ball pusher servo left

	// DIO
	rightEncoderFirstPort              = 0, // drive
	rightEncoderSecondPort             = 1, // drive
	leftEncoderFirstPort               = 2, // drive
	leftEncoderSecondPort              = 3, // drive
	bannerEncoderSpeedPort             = 4, // shooter
	bannerEncoderBallPort              = 5, // shooter

	// Analog
	shooterPivotAnglePort              = 0, // shooter angle
	shooterBallSensor                  = 1, // infrared ball sensor
	gathererArmAnglePort               = 2, // shooter

	// Spike/Relay
	circleLightPort                    = 0, // misc
	flashLightPort                     = 1, // misc

	//Joystick
	joystickPort                       = 0,
	joystickPortTwo                    = 1;
	
	//Misc. variables
	public static final double
	shooterInSpeed                     = 0.45, //shooter
	shooterShootingSpeed               = 1.0, //shooter
	shooterSlowSpeed                   = 0.5, //shooter
	
	gathererInSpeed                    = 0.4, //gatherer arm (?)

	// 2048 ticks / rev
	// 6 * pi inch / rev
	// 6 * pi / 2048 inch / tick
	driveTrainEncoderDistancePerPulse  = 6 * Math.PI / 2048,

	deadBandValue                      = 0.15;
	
	public static boolean learnMode = false;
	public static boolean PrefsWritten = false;
	public static boolean AutoRan = false;
	public static boolean TeleopRan = false;

	public static class InitVoltages {
		public static final double
		shooterPivotRobot              = 0.80, // 0.68, // Lowest shooter position
		shooterPivotGround             = 0.92, // 0.83, // Highest shooter position
		shooterPivotMiddleLow          = 0.87, // 0.77, // Low Medium shooter position
		shooterPivotMiddleHigh         = 0.84, // 0.72, // High Medium shooter position
		shooterShiftInc                = 0.01, // Increment for manual override

		collectArmPostion              = 0.75, // Where should the arm be to collect balls
		gathererArmBeforeHitRobot      = 0.15, // Highest gatherer arm position (vertical)
		gathererArmBeforeHitGround     = 0.87, // Lowest gatherer arm position (flat)
		gathererArmMid                 = 0.51, //mid gatherer arm position (avg between high and low)
		gathererShiftInc               = 0.05, // gathers shift increment

		kickerRightMin                 = 1.0, // NEED TO CHANGE distance out when kicking
		kickerRightMax                 = 0.0, // distance out when retracting
		kickerLeftMin                  = 0, // NEED TO CHANGE distance out when retracting (unmeasured)
		kickerLeftMax                  = 1.0, // distance out when kicking (unmeasured)
		
		shooterPivotkP                 = -3.5, 
		shooterPivotkI                 = 0.0,
		shooterPivotkD                 = -0.3,
		
		gathererArmkP                  = 4.0,
		gathererArmkI                  = 0.0,
		gathererArmkD                  = 0.5;
		
	}
	
	// save variables to hold preferences values.
	public static class Voltages {
		public static double
		shooterPivotRobot, 			// Lowest shooter position
		shooterPivotGround, 		// Highest shooter position
		shooterPivotMiddleLow,      // Low Medium shooter position
		shooterPivotMiddleHigh,     // High Medium shooter position
	
		collectArmPostion,          // Where should the arm be to collect balls
		gathererArmBeforeHitRobot,  // Highest gatherer arm position (vertical)
		gathererArmBeforeHitGround, // Lowest gatherer arm position (flat)
		gathererArmMid,             //mid gatherer arm position (avg between high and low)	

		shooterPivotkP, 
		shooterPivotkI,
		shooterPivotkD,
		
		gathererArmkP,
		gathererArmkI,
		gathererArmkD;

	}

	
	public static class PDP {
		public static class Port {
			public static final int
			leftDriveRear          = 0,
			leftDriveFront         = 1,
			rightDriveFront        = 2,
			rightDriveRear         = 3,
			gatherRoller           = 4,
			shooterLift            = 5,
			
			cameraLight            = 10,
			flashLight             = 11,
			shooterWheelsRight     = 12,
			shooterWheelsLeft      = 13,
			armLiftFirst           = 14,
			armLiftSecond          = 15;
		}

		public static class Limit {
			public static final double
			// amps
			leftDriveRear          = 40,
			leftDriveFront         = 40,
			rightDriveFront        = 40,
			rightDriveRear         = 40,
			gatherRoller           = 30,
			shooterLift            = 30,
			
			cameraLight            = 20,
			flashLight             = 20,
			shooterWheelsRight     = 40,
			shooterWheelsLeft      = 40,
			armLiftFirst           = 40,
			armLiftSecond          = 40;
		}
	}
}
