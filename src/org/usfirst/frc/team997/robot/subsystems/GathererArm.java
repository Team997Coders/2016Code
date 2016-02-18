package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;
import org.usfirst.frc.team997.robot.commands.GatherTrigger;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
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
    	super("gathererArm", 8.0, 0.0, 0.3);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(0.3);
    	getPIDController().setInputRange(0.2, 4.8);
        getPIDController().setOutputRange(-0.5, 0.5);
    	LiveWindow.addActuator("GathererArm", "ArmPositionController", getPIDController());

    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	armMotor = new VictorSP(gatherArmMotorPort);
    	LiveWindow.addActuator("GathererArm", "ArmAngleMotor", armMotor);
    	armAngle = new AnalogInput(armAnglePort);
    	LiveWindow.addSensor("GathererArm", "ArmAngleSensor", armAngle);
    	
    	setSetpoint(RobotMap.Voltages.gathererArmBeforeHitRobot);
    	enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GatherTrigger());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	//return armAngle.getAverageVoltage() / RobotMap.Voltages.gathererArmBeforeHitRobot;  //TODO NEED TO DIVIDE BY MAX VOLTAGE(CURRENTLY UNKNOWN)
    	
    	// remember the arm feedback is backwards!
    	return 5.0-armAngle.getAverageVoltage();
    }
    
    protected void usePIDOutput(double voltage) {
    	armMotor.pidWrite(voltage);
    }
    
    public void lockArmPosition() {
    	setSetpoint(armAngle.getAverageVoltage());
    	System.out.println("Armn Position Locked at"+armAngle.getAverageVoltage());
    	enable();
    }

    public void safeVoltage(double voltage) {
    	double angle = armAngle.getAverageVoltage();
    	// gatherer arm goes from low=5.5 to high=2.3 in reverse
    	// we don't want the arm going higher than 2.3 or lower than 5.5
    	// positive voltage makes the arm go up.
    	if (angle > RobotMap.Voltages.gathererArmBeforeHitGround && voltage < 0 ) {
    		// arm is down and we don't want it to go lower
    		lockArmPosition();
    	} else if (angle < RobotMap.Voltages.gathererArmBeforeHitRobot && voltage > 0 ) {
    		// arm is up and we don't want it going any further back
    	    lockArmPosition();
    	} else {
    		armMotor.set(voltage);
    	}
    }
    
    public void smartdashboardupdate(){
    	SmartDashboard.putNumber("Setpoint: This is its curent value", super.getSetpoint());
    	SmartDashboard.putNumber("Position: This is the current position", super.getPosition());
    	SmartDashboard.putNumber("Gatherer Arm Feedback Pot Voltage", armAngle.getAverageVoltage());
    	SmartDashboard.putNumber("Gatherer Arm Error Term", getPIDController().getError());
    	SmartDashboard.putBoolean("GathererArm PID Status", getPIDController().isEnabled());
    	SmartDashboard.putBoolean("GathererArm On Target?", super.onTarget());
    }
}
