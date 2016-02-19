package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */	
public class ShooterPivot extends PIDSubsystem {
	private Talon pivotMotor;
	private AnalogPotentiometer shootAngle;

	public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
		super("shooterPivot", 3.0, 0.0, 0.3);
    	getPIDController().setContinuous(false);
    	getPIDController().setInputRange(RobotMap.Voltages.shooterPivotMin, RobotMap.Voltages.shooterPivotMax);
        getPIDController().setOutputRange(-0.5, 0.5);
    	getPIDController().setAbsoluteTolerance(0.2);

    	pivotMotor = new Talon(aimingMotorPort);
    	shootAngle = new AnalogPotentiometer(shooterAnglePort);
    	
    	setSetpoint(RobotMap.Voltages.shooterPivotMin); //ARBRITARY; I honestly do not know what this might do to the robot
    	enable();
    	
    	LiveWindow.addActuator("ShooterPivot", "ShooterPositionController", getPIDController());
    	LiveWindow.addActuator("ShooterPivot", "ShooterAngleMotor", this.pivotMotor);
    	LiveWindow.addSensor("ShooterPivot", "ShooterAngleSensor", this.shootAngle);
	}

    protected double returnPIDInput() {
    	return shootAngle.get();   
    }
    
    private double getAngle() {
    	return shootAngle.get();
    }
    
    private double safeLocation(double voltage) {
    	if (voltage == 0 ||
    		getAngle() > RobotMap.Voltages.shooterPivotMax ||
        	getAngle() < RobotMap.Voltages.shooterPivotMin) {
    		return 0;
    	} else {
    		return voltage;
    	}
    }
    private double safeVoltage(double voltage) {
    	//checks if voltage/current w/in safe ranges. If not, sets motor to 0
    	if (voltage == 0 ||
    		Robot.pdp.getCurrent(RobotMap.PDP.Port.shooterLift) > RobotMap.PDP.Limit.shooterLift) {
    		return 0;
    	} else {
    		//sets motor to (safe) voltage
    		return voltage;
    	}
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	pivotMotor.pidWrite(output);
    	//pivotMotor.pidWrite(safeLocation(safeVoltage(output)));  //TODO need safety measures, min / max and currrent *DONE JULIA*
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void smartDashboard() {
    	SmartDashboard.putNumber("Shooter Pivot", shootAngle.get());
    }
}

