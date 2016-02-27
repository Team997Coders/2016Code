package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.commands.GatherTriggerNoSP;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class GathererArmNoSP extends Subsystem {
    private VictorSP armMotor;
    
	public GathererArmNoSP(int motorPort) {
		armMotor = new VictorSP(motorPort);
	}
	
	// backwards compatibility with GathererArm
	public void safeVoltage(double voltage) {
		armMotor.set(voltage);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new GatherTriggerNoSP());
    }
    
    public void enable() {}

    public void disable() {}
    
    public void setSetpoint(double _1) {}
    
    public boolean onTarget() {return true;}
	
    public void smartDashboard() {}
	
    public void lockArmPosition() {}
	
    public double getSetpoint() {return 0;}
	
    public double getPosition() {return 0;}
    
}

