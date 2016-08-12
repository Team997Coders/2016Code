package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.GatherTrigger;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GathererArm extends PIDSubsystem {
	private VictorSP armMotor;
	private AnalogPotentiometer armAngle;
	private static final double absoluteTolerance = 0.01;
	
    public GathererArm(int gatherArmMotorPort, int armAnglePort) {
    	super("gathererArm", 1.0, 0.0, 0.5);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(absoluteTolerance);
    	getPIDController().setInputRange(RobotMap.Voltages.gathererArmBeforeHitGround, 
    			RobotMap.Voltages.gathererArmBeforeHitGround);
        getPIDController().setOutputRange(-0.5, 0.5);    

    	armMotor = new VictorSP(gatherArmMotorPort);
    	armAngle = new AnalogPotentiometer(armAnglePort);

    	LiveWindow.addActuator("GathererArm", "ArmPositionController", getPIDController());
    	LiveWindow.addActuator("GathererArm", "ArmAngleMotor", armMotor);
    	LiveWindow.addSensor("GathererArm", "ArmAngleSensor", armAngle);

    	lockArmPosition();
    	//setSetpoint(RobotMap.Voltages.gathererArmBeforeHitGround - 0.05);
    	//enable();
    }
    
//    public boolean onTarget() {
//    	return Math.abs(getPosition() - getSetpoint()) < absoluteTolerance;
//    }

    @Override
	public void initDefaultCommand() {
    	setDefaultCommand(new GatherTrigger());
    }
    
    @Override
	protected double returnPIDInput() {
    	//return armAngle.getAverageVoltage() / RobotMap.Voltages.gathererArmBeforeHitRobot;  //TODO NEED TO DIVIDE BY MAX VOLTAGE(CURRENTLY UNKNOWN)
    	
    	//remember the arm feedback is backwards!
    	return armAngle.get();
    }
  
    @Override
	protected void usePIDOutput(double voltage) {
    	armMotor.pidWrite(voltage);
    }
    
    public void lockArmPosition() {
    	setSetpoint(armAngle.get());
    	System.out.println("Armn Position Locked at"+armAngle.get());
    	enable();
    }

    public void safeVoltage(double voltage) {
    	double angle = armAngle.get();
    	// gatherer arm goes from low=5.5 to high=2.3 in reverse
    	// we don't want the arm going higher than 2.3 or lower than 5.5
    	// positive voltage makes the arm go up.
    	if (angle > RobotMap.Voltages.gathererArmBeforeHitGround && voltage > 0) {
    		// arm is down and we don't want it to go lower
    		lockArmPosition();
    	} else if (angle < RobotMap.Voltages.gathererArmBeforeHitRobot && voltage < 0) {
    		// arm is up and we don't want it going any further back
    	    lockArmPosition();
    	} else {
    		armMotor.set(voltage);
    	}
    }
    
    public void smartDashboard() {
    	SmartDashboard.putNumber("GathererArm Setpoint", super.getSetpoint());
    	SmartDashboard.putNumber("GathererArm Angle", armAngle.get());
    	SmartDashboard.putNumber("GathererArm Average Error Term", getPIDController().getAvgError());
    	SmartDashboard.putNumber("GathererArm Error Term", getPIDController().getError());
    	SmartDashboard.putBoolean("GathererArm PID Status", getPIDController().isEnabled());
    	SmartDashboard.putBoolean("GathererArm On Target?", super.onTarget());
    }
}
