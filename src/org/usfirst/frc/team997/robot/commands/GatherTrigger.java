package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GatherTrigger extends Command {

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
    	Robot.gathererarm.trigger(Robot.oi.getGathererVoltage());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
