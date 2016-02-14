package org.usfirst.frc.team997.robot;

public class RobotMap {
	static final int
	// NAME                  = PORT

	// PWM
	leftMotorPort            = 0, // drive
	rightMotorPort           = 1, // drive
	shooterMotorPort         = 2, // shooter
	gatherArmMotorPort       = 3, // gatherer arm
	rollerMotorPort          = 4, // gatherer roller
	aimingMotorPort          = 5, // aimer.  shooter lifter?
	servoMotorFirstPort      = 8, // ball pusher servo left
	servoMotorSecondPort     = 9, // ball pusher servo right

	// DIO
	rightEncoderFirstPort    = 0, // drive
	rightEncoderSecondPort   = 1, // drive
	leftEncoderFirstPort     = 2, // drive
	leftEncoderSecondPort    = 3, // drive
	bannerEncoderSpeedPort   = 4, // shooter
	bannerEncoderBallPort    = 5, // shooter

	// Analog
	gyroPort                 = 0, // drive
	ultrasonicPort           = 1, // drive
	armAnglePort             = 2, // shooter
	shooterAnglePort         = 3, // shooter angle

	// Spike/Relay
	circleLightPort          = 0, // misc
	flashLightPort           = 1, // misc

	//Joystick
	joystickPort             = 0,
	joystickPortTwo          = 1;
	
	//Misc. variables
	public static final double
	maxAccelDrive            = 10,
	lowPoint                 = 0,
	midPoint                 = 21,
	highPoint                = 42,
	armLow                   = 0,
	armHigh                  = 42,
	collectBallArmPos        = 0;

	public static class Voltages {
		public static final double
		shooterPivotMin      = 0, // arbitrary
		shooterPivotMax      = 42, // arbitrary
		gathererMax          = 20, // arbitrary
		gathererArmMin       = 0, // arbitrary
		gathererArmMax       = 45; // arbitrary
	}
}
