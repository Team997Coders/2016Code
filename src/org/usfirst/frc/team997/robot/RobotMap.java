package org.usfirst.frc.team997.robot;

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
	gyroPort                           = 0, // drive
	ultrasonicPort                     = 1, // drive
	armAnglePort                       = 2, // shooter
	shooterAnglePort                   = 3, // shooter angle
	

	// Spike/Relay
	circleLightPort                    = 0, // misc
	flashLightPort                     = 1, // misc

	//Joystick
	joystickPort                       = 0,
	joystickPortTwo                    = 1;
	
	//Misc. variables
	public static final double
	maxAccelDrive                      = /*1*/0,
	lowPoint                           = 0.12, //Shooter
	rampAngle                          = 0.20, //Shooter to go over defenses
	midPoint                           = 0.27, //shooter
	highPoint                          = 42, //ARBITRARY shooter
	armLow                             = 0.75, //arm
	armHigh                            = 0.28, //ARM
	collectBallArmPos                  = 0, //ARBITRARY arm

	shooterInSpeed                     = 0.45, //shooter
	shooterShootingSpeed               = 0.6, //shooter
	
	gathererInSpeed                    = 0.4, //gatherer arm (?)

	kickerRightMin                     = 0.5, // NEED TO CHANGE distance out when kicking
	kickerRightMax                     = 0.0, // distance out when retracting
	kickerLeftMin                      = 0, // NEED TO CHANGE distance out when retracting (unmeasured)
	kickerLeftMax                      = 1.0, // distance out when kicking (unmeasured)
	
	deadBandValue                      = 0.15;

	public static class Voltages {
		public static final double
		shooterPivotMin               	= 0, // arbitrary
		shooterPivotMax               	= 42, // arbitrary
		gathererMax                   	= 20, // arbitrary current
		gathererArmBeforeHitRobot     	= 1, // arbitrary (NOT 0) voltage
		gathererArmBeforeHitGround    	= 3.8; // arbitrary voltage
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
