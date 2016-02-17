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
	public static final double maxCurrent = RobotMap.Voltages.gathererArmBeforeHitGround /*>>> 40 */; //TODO Angle must never go past 90
	
    // Initialize your subsystem here
    public GathererArm(int gatherArmMotorPort, int armAnglePort) {
     //"super" MUST BE FIRST LINE OF CODE!!!!!!!
    	super("gathererArm", 5.0, 0.0, 0.3);
    	getPIDController().setContinuous(false);
    	getPIDController().setAbsoluteTolerance(0.2);
    	getPIDController().setInputRange(0.0, 5.0);
        getPIDController().setOutputRange(-1.0, 1.0);
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
    	return getPosition();
    }
    
    public double getPosition() { return armAngle.getAverageVoltage(); }
    
    public void safeVoltage(double voltage) {
    	//Checks if the two voltages and if they are not within safe ranges sets motor to zero
    	SmartDashboard.putNumber("Gatherer Arm Input safeVoltage", voltage);
    	voltage /= 2;
    	voltage = safeLocation(voltage);
    	SmartDashboard.putNumber("Gatherer Arm After safeLocation", voltage);
    	if (voltage < -1) {
    		armMotor.set(-1);
    	} else if (voltage > 1) {
    		armMotor.set(1);
    	} else {
    		armMotor.set(voltage);
    	}
    }
    
    private double safeLocation(double voltage) {
    	if(armAngle.getAverageVoltage() < RobotMap.Voltages.gathererArmBeforeHitRobot && voltage < 0) {
    		return 0;
    	} else if(armAngle.getAverageVoltage() > RobotMap.Voltages.gathererArmBeforeHitGround && voltage > 0) {
    		return 0;
    	} else {
    		return voltage;
    	}
    }
    
    protected void usePIDOutput(double voltage) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	if(Robot.pdp.getCurrent(14) > maxCurrent || Robot.pdp.getCurrent(15) > maxCurrent) {
    		armMotor.set(0);
    	} else {
    	
    		//sets motor to voltage (0 if unsafe)
    		armMotor.set(safeLocation(voltage));
    	}
    }
    
    public void smartdashboardupdate(){
    	SmartDashboard.putNumber("Setpoint: This is its curent value", super.getSetpoint());
    	SmartDashboard.putNumber("Position: This is the current position", super.getPosition());
    	SmartDashboard.putNumber("Gatherer Arm", armAngle.getAverageVoltage());
    	SmartDashboard.putBoolean("GathererArm PID Status", getPIDController().isEnabled());
    	SmartDashboard.putBoolean("GathererArm On Target?", super.onTarget());
    }
}
