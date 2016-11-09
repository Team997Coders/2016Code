package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.OI;
import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GatherTrigger extends Command {
	
	public boolean locked = false;

	/**this command takes the triggers input and moves the position of the gatherer based upon them. 
	 * 
	 */

	public GatherTrigger() {
    	requires(Robot.gathererArm);
    }

    @Override
	protected void initialize() {}

    @Override
	protected void execute() {
    	double dead = OI.deadBand((Robot.oi.getRawTriggerAxis())*0.7);
    	SmartDashboard.putNumber("GatherTrigger", dead);
    	if (dead != 0) {
    		Robot.gathererArm.disable();
    		SmartDashboard.putNumber("GatherTrigger manual", dead);
    		Robot.gathererArm.safeVoltage(dead);
    		locked = false;
    	} else if (!locked) {
    		double pos = Robot.gathererArm.getPosition();
    		if (pos > RobotMap.Voltages.gathererArmBeforeHitGround)
    			pos = RobotMap.Voltages.gathererArmBeforeHitGround;
    		else if (pos < RobotMap.Voltages.gathererArmBeforeHitRobot)
    			pos = RobotMap.Voltages.gathererArmBeforeHitRobot;
    		Robot.gathererArm.setSetpoint(pos);
    		Robot.gathererArm.enable();
    		locked = true;
    		System.out.println("Arm Locked");
    	} else {
    		Robot.gathererArm.enable();
    	}
    }

    @Override
	protected boolean isFinished() {
        return Robot.gathererArm.onTarget();
    }

    @Override
	protected void end() {}

    @Override
	protected void interrupted() {}
}
