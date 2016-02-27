package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Gatherer extends Subsystem {
	private VictorSP rollerMotor;

	public Gatherer(int rollerPort) { 
		rollerMotor = new VictorSP(rollerPort);
	}

	public void safeValue(double voltage) {
		if(Robot.pdp.getCurrent(RobotMap.PDP.Port.gatherRoller) > RobotMap.PDP.Limit.gatherRoller) {
			rollerMotor.set(0);
		}
		
		else {
			rollerMotor.set(voltage);
			//sets motor to non-explosive voltage
		}
	}

	public void gathervoltage(double rollerSpeed) {
		safeValue(rollerSpeed);
	}

	public void initDefaultCommand() {}
}

