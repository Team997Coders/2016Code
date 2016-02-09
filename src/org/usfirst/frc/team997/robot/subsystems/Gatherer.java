package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gatherer extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	
	private VictorSP rollerMotor;
	
public Gatherer(int rollerPort) {
	
	 
	rollerMotor = new VictorSP(rollerPort);
}

public void gathervoltage(double rollerSpeed) {
	
	rollerMotor.set(rollerSpeed);
}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

