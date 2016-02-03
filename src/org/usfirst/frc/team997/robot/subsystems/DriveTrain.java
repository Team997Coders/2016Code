package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.commands.TankDrive;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private VictorSP leftmotor;
	private VictorSP rightmotor;
	
	public DriveTrain(int leftPort, int rightPort) {
		
		leftmotor = new VictorSP(leftPort);
		rightmotor = new VictorSP(rightPort);
		
	}
	
	public void drivevoltage(double leftspeed, double rightspeed){
		leftmotor.set(-leftspeed);
		rightmotor.set(rightspeed);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new TankDrive());
    }
}

