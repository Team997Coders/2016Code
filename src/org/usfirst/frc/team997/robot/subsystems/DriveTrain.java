package org.usfirst.frc.team997.robot.subsystems;

//import org.usfirst.frc.team997.robot.commands.TankDrive;

import org.usfirst.frc.team997.robot.commands.ArcadeDrive;
import org.usfirst.frc.team997.robot.commands.ExampleCommand;
import org.usfirst.frc.team997.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
//this is saying that leftmotor and rightmotor are VictorSP
public class DriveTrain extends Subsystem {
	private VictorSP leftmotor;
	private VictorSP rightmotor;
	
	private int gear; 
	//this is saying where to find the VictorSP
	public DriveTrain(int leftPort, int rightPort) {
		leftmotor = new VictorSP(leftPort);
		rightmotor = new VictorSP(rightPort);
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
	public void driveVoltage(double leftspeed, double rightspeed){
		if(gear == 1){
			leftmotor.set(-leftspeed/3);
			rightmotor.set(rightspeed/3);
			} 
		else {
			leftmotor.set(-leftspeed);
			rightmotor.set(rightspeed);
			}
		
		}
	
	public void smartDashboard(){
		SmartDashboard.putNumber("gear value" , getGear());
	}
	//it sets things and stuff to Arcade drive
	protected void initDefaultCommand() {
		//setDefaultCommand( new ExampleCommand());
		setDefaultCommand(new TankDrive());
	// TODO Auto-generated method stub
   //setDefaultCommand(new MySpecialCommand());
         
	}
}

