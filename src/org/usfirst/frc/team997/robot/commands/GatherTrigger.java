package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.OI;
import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GatherTrigger extends Command {
	
	public boolean locked=false;

	/**this command takes the triggers input and moves the position of the gatherer based upon them. 
	 * 
	 */
    public GatherTrigger() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gathererarm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double dead = OI.deadband(Robot.oi.getRawTriggerAxis());
    	SmartDashboard.putNumber("GatherTrigger", dead);
    	if (dead != 0) {
    		Robot.gathererarm.disable();
    		SmartDashboard.putNumber("GatherTrigger manual", dead);
    		Robot.gathererarm.safeVoltage(dead);
    		locked=false;
    	} else if (!locked) {
    		double pos = Robot.gathererarm.getPosition();
    		if (pos > RobotMap.Voltages.gathererArmBeforeHitGround)
    			pos = RobotMap.Voltages.gathererArmBeforeHitGround;
    		else if (pos < RobotMap.Voltages.gathererArmBeforeHitRobot)
    			pos = RobotMap.Voltages.gathererArmBeforeHitRobot;
    		Robot.gathererarm.setSetpoint(pos);
    		Robot.gathererarm.enable();
    		locked = true;
    		System.out.println("Arm Locked");
    	} else {
    		Robot.gathererarm.enable();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.gathererarm.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
