package org.usfirst.frc.team997.robot;

public class RobotMap {
	public static final int
	// NAME                  = PORT

	// PWM
	leftMotorPort            = 0, // drive
	rightMotorPort           = 1, // drive
	shooterMotorPort         = 2, // shooter
	shooterAngleMotorPort    = 4, // shooter
	shooterServo			 = 6, // shooter

	// DIO
	leftEncoderFirstPort     = 0, // drive
	leftEncoderSecondPort    = 1, // drive
	rightEncoderFirstPort    = 2, // drive
	rightEncoderSecondPort   = 3, // drive
	shooterEncoderFirstPort  = 6, // shooter SPEED SENSOR
	shooterEncoderSecondPort = 7, // shooter BALL SENSOR

	// Analog
	gyroPort                 = 0, // drive
	ultrasonicPort           = 1, // drive
	hallEffectPort           = 2, // shooter

	// Spike
	flashLightPort           = 0, // misc
	circleLightPort          = 1; // misc
}
