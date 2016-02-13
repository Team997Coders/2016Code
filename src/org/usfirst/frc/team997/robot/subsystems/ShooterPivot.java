package org.usfirst.frc.team997.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */	
public class ShooterPivot extends PIDSubsystem {
	
	private VictorSP pivotMotor;
	private AnalogInput shootAngle;
	public static final double MIN = 42, MAX = 147;//aribitrary..but the meaning of life.
	
	public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
		super("shooterPivot", 1, 0, 0);
    	getPIDController().setContinuous(false);
    	setSetpoint(MIN); //ARBRITARY; I honestly do not know what this might do to the robot
    	enable();

    	pivotMotor = new VictorSP(aimingMotorPort);
    	shootAngle = new AnalogInput(shooterAnglePort);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return shootAngle.getAverageVoltage() / MAX; //THIS IS ARBRITARY; you probably shouldn't run it cuz things might go wrong  
    	}
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	pivotMotor.set(output);  //TODO need safety measures, min / max and currrent
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

