package org.usfirst.frc.team997.robot;

public class RobotMap {
	public static final int
	// NAME                  = PORT

	// PWM
	leftMotorPort            = 0, // drive
	rightMotorPort           = 1, // drive
	shooterMotorPort         = 3, // shooter
	shooterAngleMotorPort    = 4, // shooter
	servoMotorPort           = 5, // ball pusher servo
	aimingMotorPort          = 6, // aimer
 
	// DIO
	rightEncoderFirstPort    = 0, // drive
	rightEncoderSecondPort   = 1, // drive
	leftEncoderFirstPort     = 2, // drive
	leftEncoderSecondPort    = 3, // drive
	shooterEncoderFirstPort  = 4, // shooter
	shooterEncoderSecondPort = 5, // shooter

	// Analog
	gyroPort                 = 0, // drive
	ultrasonicPort           = 1, // drive
	hallEffectPort           = 2, // shooter
	shooterAnglePort         = 3, // shooter angle

	// Spike/Relay
	circleLightPort          = 0, // misc
	flashLightPort           = 1, // misc
	
	//Joystick
	JoystickPort             = 0;
}
