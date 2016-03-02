package org.usfirst.frc.team997.robot.subsystems;

//import org.usfirst.frc.team997.robot.commands.TankDrive;

import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
import org.usfirst.frc.team997.robot.commands.TankDrive;
import org.usfirst.frc.team997.robot.commands.TankSquared;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private PIDController left, right;
	// left reversed
	private Encoder leftEncoder, rightEncoder;
	
	public DriveTrain(int leftPort, int rightPort,
			          int leftEncoderFirstPort, int leftEncoderSecondPort,
			          int rightEncoderFirstPort, int rightEncoderSecondPort,
			          double inchesPerTick) {
		leftEncoder = new Encoder(leftEncoderFirstPort, leftEncoderSecondPort);
		leftEncoder.setDistancePerPulse(inchesPerTick);
		leftEncoder.setPIDSourceType(PIDSourceType.kRate);
		leftEncoder.setReverseDirection(true);
		rightEncoder = new Encoder(rightEncoderFirstPort, rightEncoderSecondPort);
		rightEncoder.setDistancePerPulse(inchesPerTick);
		rightEncoder.setPIDSourceType(PIDSourceType.kRate);
		left = new PIDController(3, 0, 0, leftEncoder, new VictorSP(leftPort));
		left.setInputRange(0, 30000);
		right = new PIDController(3, 0, 0, rightEncoder, new VictorSP(rightPort));
		right.setInputRange(0, 30000);
	}
	
	// also checks the gear status so then if gear == 1 the speed is halved and if its 0 its set at full speed.
//	public void driveVoltage(double leftspeed, double rightspeed){
//		if(gear == 1){
//			leftmotor.set(-leftspeed/2);
//			rightmotor.set(rightspeed/2);
//		} 
//		else if (gear == 0) {
//			leftmotor.set(-leftspeed );
//			rightmotor.set(rightspeed );
//		}
//	}

	public void driveVoltage(double leftSpeed, double rightSpeed) {
		SmartDashboard.putNumber("driveVoltage Left", leftSpeed);
		SmartDashboard.putNumber("driveVoltage Right", rightSpeed);
		left.setSetpoint(leftSpeed * 2048);
		right.setSetpoint(rightSpeed * 2048);
		/*this.leftmotor.setDesiredVelocity(leftSpeed);
		this.rightmotor.setDesiredVelocity(-rightSpeed);*/
	}
	
	public void smartDashboard(){
		SmartDashboard.putNumber("DriveTrain Encoder Left Rate", this.leftEncoder.getRate());
		SmartDashboard.putNumber("DriveTrain Encoder Right Rate", this.rightEncoder.getRate());
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
		//setDefaultCommand(new TankDrive());
		//setDefaultCommand(new TankSquared());
	}
}
