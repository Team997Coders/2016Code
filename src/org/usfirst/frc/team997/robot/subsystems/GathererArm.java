package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class GathererArm extends PIDSubsystem {

	private VictorSP armMotor;
	private AnalogInput armAngle;
	public static final double MIN = 42,
	MAX = 147; //
	
    // Initialize your subsystem here
    public GathererArm() {
     //"super" MUST BE FIRST LINE OF CODE!!!!!!!
    	super("gathererArm", 1.0, 0.0, 0.0);
    	getPIDController().setContinuous(false);
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(MIN);
    	enable();
    	
    	armMotor = new VictorSP(RobotMap.gatherArmMotorPort);
    	armAngle = new AnalogInput(RobotMap.armAnglePort);
    
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return armAngle.getAverageVoltage();  //NEED TO DIVIDE BY MAX VOLTAGE(CURRENTLY UNKNOWN)
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	armMotor.set(output);
    }
}
