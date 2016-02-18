package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */	
public class ShooterPivot extends PIDSubsystem {
	
	private Talon pivotMotor;
	private AnalogPotentiometer shootAngle;
	public static final double maxCurrent = 30 ; //arbitrary ..but the meaning of life.
	
	public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
		super("shooterPivot", 3.0, 0.0, 0.3);
    	getPIDController().setContinuous(false);
    	getPIDController().setInputRange(0.2, 4.8);
        getPIDController().setOutputRange(-0.5, 0.5);
    	getPIDController().setAbsoluteTolerance(0.2);

    	pivotMotor = new Talon(aimingMotorPort);
    	shootAngle = new AnalogPotentiometer(shooterAnglePort);
    	
    	setSetpoint(RobotMap.Voltages.shooterPivotMin); //ARBRITARY; I honestly do not know what this might do to the robot
    	enable();
	}

    protected double returnPIDInput() {
    	// TODO THIS IS ARBRITARY; you probably shouldn't run
    	// it cuz things might go wrong
    	return shootAngle.get();   
    }
    
    public double safeVoltage(double voltage) {
    	//checks if voltage/current w/in safe ranges. If not, sets motor to 0
    	if(shootAngle.get() > RobotMap.Voltages.shooterPivotMax) {
    		return 0;
    	} else if(shootAngle.get() < RobotMap.Voltages.shooterPivotMin) {
    		return 0;
    	} else if(Robot.pdp.getCurrent(5) > maxCurrent) {
    		return 0;
    	}
    	
    	//sets motor to (safe) voltage
    	return voltage/5;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	pivotMotor.pidWrite(safeVoltage(output));  //TODO need safety measures, min / max and currrent *DONE JULIA*
    }
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void smartDashboard() {
    	SmartDashboard.putNumber("Shooter Pivot", shootAngle.get());
    }
}

