package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gatherer extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
public static final double maxCurrent = 20;	
private VictorSP rollerMotor;
	
public Gatherer(int rollerPort) { 
	rollerMotor = new VictorSP(rollerPort);
}

public void safeValue(double voltage) {
	//checks if the motor is using too much current
	if(Robot.pdp.getCurrent(4) > maxCurrent) {
		rollerMotor.set(0);
	}
	
	//sets motor to non-explosive voltage
	rollerMotor.set(voltage);
}

public void gathervoltage(double rollerSpeed) {
	safeValue(rollerSpeed);
}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

