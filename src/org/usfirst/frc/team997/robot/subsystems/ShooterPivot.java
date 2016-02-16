package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

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
	public static final double maxCurrent = 30 ; //arbitrary ..but the meaning of life.
	
	public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
		super("shooterPivot", 3.0, 0.0, 0.3);
    	getPIDController().setContinuous(false);
    	setSetpoint(RobotMap.Voltages.shooterPivotMin); //ARBRITARY; I honestly do not know what this might do to the robot
    	enable();

    	pivotMotor = new Talon(aimingMotorPort);
    	shootAngle = new AnalogInput(shooterAnglePort);
	}

    protected double returnPIDInput() {
    	// TODO THIS IS ARBRITARY; you probably shouldn't run
    	// it cuz things might go wrong
    	return shootAngle.getAverageVoltage() / RobotMap.Voltages.shooterPivotMax;   
    }
    
    public void safeVoltage(double voltage) {
    	//checks if voltage/current w/in safe ranges. If not, sets motor to 0
    	if(shootAngle.getAverageVoltage() > RobotMap.Voltages.shooterPivotMax) {
    		pivotMotor.set(0);
    	} else if(shootAngle.getAverageVoltage() < RobotMap.Voltages.shooterPivotMin) {
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

