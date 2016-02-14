package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */	
public class ShooterPivot extends PIDSubsystem {
	
	private Talon pivotMotor;
	private AnalogInput shootAngle;
	public static final double MIN = 0, MAX = 42, maxCurrent = 30 ; //arbitrary ..but the meaning of life.
	
	public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
		super("shooterPivot", 1, 0, 0);
    	getPIDController().setContinuous(false);
    	setSetpoint(MIN); //ARBRITARY; I honestly do not know what this might do to the robot
    	enable();

    	pivotMotor = new Talon(aimingMotorPort);
    	shootAngle = new AnalogInput(shooterAnglePort);
	}
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return shootAngle.getAverageVoltage() / MAX; //TODO THIS IS ARBRITARY; you probably shouldn't run it cuz things might go wrong  
    	}
    
    public void safeVoltage(double voltage) {
    	//checks if voltage/current w/in safe ranges. If not, sets motor to 0
    	if(shootAngle.getAverageVoltage() > MAX) {
    		pivotMotor.set(0);
    	} else if(shootAngle.getAverageVoltage() < MIN) {
    		pivotMotor.set(0);
    	} else if(Robot.pdp.getCurrent(5) > maxCurrent) {
    		pivotMotor.set(0);
    	}
    	
    	//sets motor to (safe) voltage
    	pivotMotor.set(voltage);
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	safeVoltage(output);  //TODO need safety measures, min / max and currrent *DONE JULIA*
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

