package org.usfirst.frc.team997.robot.subsystems;

//import org.usfirst.frc.team997.robot.commands.TankDrive;

import org.usfirst.frc.team997.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
//this is saying that leftmotor and rightmotor are VictorSP
public class DriveTrain extends Subsystem {
	private VictorSP leftmotor;
	private VictorSP rightmotor;
	//this is saying where to find the VictorSP
	public DriveTrain(int leftPort, int rightPort) {
		leftmotor = new VictorSP(leftPort);
		rightmotor = new VictorSP(rightPort);
	}
	//this making it so the joystick and have a lovely conversation with the motors
	public void drivevoltage(double leftspeed, double rightspeed){
		leftmotor.set(-leftspeed);
		rightmotor.set(rightspeed);
	}
	//it sets things and stuff to tank drive
	protected void initDefaultCommand() {
		setDefaultCommand(new TankDrive());
		// TODO Auto-generated method stub
		//setDefaultCommand(new MySpecialCommand());

	}
}

