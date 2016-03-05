package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
//import org.usfirst.frc.team997.robot.commands.TankDrive;
//import org.usfirst.frc.team997.robot.commands.TankSquared;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
//this is saying that leftmotor and rightmotor are VictorSP
public class DriveTrain extends Subsystem {
	private VictorSP left, right;
	private Encoder leftEncoder, rightEncoder;
	
	public DriveTrain(int leftPort, int rightPort,
			          int leftEncoderFirstPort, int leftEncoderSecondPort,
			          int rightEncoderFirstPort, int rightEncoderSecondPort) {
		left = new VictorSP(leftPort);
		right = new VictorSP(rightPort);

		leftEncoder = new Encoder(leftEncoderFirstPort, leftEncoderSecondPort);
		leftEncoder.reset();
		leftEncoder.setDistancePerPulse(RobotMap.driveTrainEncoderDistancePerPulse);
		
		rightEncoder = new Encoder(rightEncoderFirstPort, rightEncoderSecondPort);
		rightEncoder.reset();
		rightEncoder.setDistancePerPulse(RobotMap.driveTrainEncoderDistancePerPulse);
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
		this.left.pidWrite(leftSpeed);
		this.right.pidWrite(-rightSpeed);
	}
	
	public void smartDashboard(){
		SmartDashboard.putNumber("DriveTrain Encoder Left Rate", this.leftEncoder.getRate());
		SmartDashboard.putNumber("DriveTrain Encoder Left Distance", this.leftEncoder.getDistance());
		SmartDashboard.putNumber("DriveTrain Encoder Right Rate", this.rightEncoder.getRate());
		SmartDashboard.putNumber("DriveTrain Encoder Right Distance", this.rightEncoder.getDistance());
	}

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
		//setDefaultCommand(new TankDrive());
		//setDefaultCommand(new TankSquared());
	}
}
