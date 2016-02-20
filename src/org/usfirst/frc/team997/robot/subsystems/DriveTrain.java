package org.usfirst.frc.team997.robot.subsystems;

//import org.usfirst.frc.team997.robot.commands.TankDrive;

import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
import org.usfirst.frc.team997.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
//this is saying that leftmotor and rightmotor are VictorSP
public class DriveTrain extends Subsystem {
	private VictorSP left, right;
	/*public AccelMotor leftmotor;
	public AccelMotor rightmotor;*/
	
	private int gear; 
	//Assign victor ports. 
	public DriveTrain(int leftPort, int rightPort,
			          int leftEncoderFirstPort, int leftEncoderSecondPort,
			          int rightEncoderFirstPort, int rightEncoderSecondPort,
			          double maxAccelDrive) {
		left = new VictorSP(leftPort);
		right = new VictorSP(rightPort);
		/*leftmotor = new AccelMotor(new VelMotor(new VictorSP(leftPort), 
				                                new Encoder(leftEncoderFirstPort, leftEncoderSecondPort), 0),
		                           maxAccelDrive);
		rightmotor = new AccelMotor(new VelMotor(new VictorSP(rightPort),
				                                 new Encoder(rightEncoderFirstPort, rightEncoderSecondPort), 0),
				                    maxAccelDrive);*/
	    gear = 1;
	}
	
	
	//a method that ToggleShift can use to check the status of gear and then be able to change the gear for the speeds.
	public int getGear(){
		return gear;
	}
	
	//sets the gear to the value of shiftvar which is eather 0 or 1. 
	public void shift (int shiftvar){
		if(shiftvar == 1 || shiftvar == 0){
			gear = shiftvar;
		}
	}
	
	//this is making it so the joystick and the motors can have a lovely conversation with the motors 
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
		/*this.leftmotor.setDesiredVelocity(leftSpeed);
		this.rightmotor.setDesiredVelocity(-rightSpeed);*/
	}
	
	/*public void driveVoltage(double leftspeed, double rightspeed){
		double mod = 1;
		leftmotor.set(-leftspeed * mod);
		rightmotor.set(rightspeed * mod / 1.3);
	}*/
	
	public void smartDashboard(){
		/*
		this.leftmotor.smartDashboard();
		this.rightmotor.smartDashboard();
		*/
		//SmartDashboard.putNumber("gear value" , getGear()); //not needed since we are not using gears anymore
	}
	//it sets things and stuff to Arcade drive
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
		//setDefaultCommand(new TankDrive());
	  //setDefaultCommand(new MySpecialCommand());  
	}
	
}

