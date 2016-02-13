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
	servoMotorPort           = 5, // ball pusher servo
	aimingMotorPort          = 6, // aimer

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
	
	static final double
	maxAccelDrive            = 10;
}
