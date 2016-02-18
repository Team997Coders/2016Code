package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.GatherTrigger;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GathererArm extends PIDSubsystem {
	private VictorSP armMotor;
	private AnalogInput armAngle;
	private boolean readyToSDOne = false, readyToSDTwo = false;

    // Initialize your subsystem here
    public GathererArm(int gatherArmMotorPort, int armAnglePort) {
     //"super" MUST BE FIRST LINE OF CODE!!!!!!!
    	super("gathererArm", 50.0, 0.0, 0.3);
    	getPIDController().setContinuous(false);
    	this.setAbsoluteTolerance(0.5);
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(RobotMap.Voltages.gathererArmBeforeHitRobot);
    	enable();
    	
    	armMotor = new VictorSP(gatherArmMotorPort);
    	armAngle = new AnalogInput(armAnglePort);
    
    }
    
    private double getAngle() {
    	double a = armAngle.getAverageVoltage();
    	if (a < 1) { return 5 + a; }
    	else { return a; }
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GatherTrigger());
    }
    
    protected double returnPIDInput() {
    	//return armAngle.getAverageVoltage();  //TODO NEED TO DIVIDE BY MAX VOLTAGE(CURRENTLY UNKNOWN)
    	if (this.readyToSDOne) SmartDashboard.putNumber("Input into PID function", this.getAngle());
    	this.readyToSDOne = false;
    	return this.getAngle();
    }
    
    public void safeVoltage(double voltage) {
    	//Checks if the two voltages and if they are not within safe ranges sets motor to zero
    	voltage /= 2;
    	voltage = safePDP(safeLocation(voltage));
    	armMotor.pidWrite(bound(voltage, -1, 1));
    }
    
    private double bound(double d, double min, double max) {
    	if (d < min) return min;
    	else if (d > max) return max;
    	else return d;
    }
    
    // turn off if out of bounds and not going in bounds
    private double safeLocation(double voltage) {
    	if (voltage == 0 ||
    		getAngle() < RobotMap.Voltages.gathererArmBeforeHitRobot && voltage < 0 ||
    		getAngle() > RobotMap.Voltages.gathererArmBeforeHitGround && voltage > 0) {
    		return 0;
    	} else {
    		return voltage;
    	}
    }

    // turn off if too much PDP voltage
    private double safePDP(double voltage) {
    	if (voltage == 0 ||
    		Robot.pdp.getCurrent(RobotMap.PDP.Port.armLiftFirst) > RobotMap.PDP.Limit.armLiftFirst ||
        	Robot.pdp.getCurrent(RobotMap.PDP.Port.armLiftSecond) > RobotMap.PDP.Limit.armLiftSecond) {
    		return 0;
    	} else {
    		return voltage;
    	}
    }
    
    protected void usePIDOutput(double voltage) {
    	//sets motor to voltage (0 if unsafe)
    	if (readyToSDTwo) SmartDashboard.putNumber("GatherArm PID Tells me to", voltage);
    	readyToSDTwo = false;
    	armMotor.pidWrite(safePDP(safeLocation(voltage)));
    }

    private boolean isEnabled = false;
    public void enable() {
    	super.enable();
    	isEnabled = true;
    }
    public void disable() {
    	super.disable();
    	isEnabled = false;
    }

    public void smartdashboardupdate() {
    	readyToSDOne = true;
    	readyToSDTwo = true;
    	SmartDashboard.putNumber("Gatherer Arm", armAngle.getAverageVoltage());
    	SmartDashboard.putNumber("Gatherer Arm Setpoint", this.getSetpoint());
    	SmartDashboard.putNumber("Gatherer Arm Position", this.getPosition());
    	SmartDashboard.putNumber("GathererArm PID", Robot.gathererarm.getAngle());
    	SmartDashboard.putBoolean("PID enabled GatherTrigger", isEnabled);
    }
}
