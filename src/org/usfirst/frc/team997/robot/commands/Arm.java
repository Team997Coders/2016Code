package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Arm extends Command {
	
	private double position;
	private boolean toggleArm;
	

    public Arm(double position) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gathererarm);
    	this.position = position;
    	
		toggleArm = true;
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gathererarm.setSetpoint(position);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(toggleArm = true) {
			Robot.oi.armPos = 43;
			toggleArm = false;
		} else if(toggleArm = false) {
			Robot.oi.armPos = 0;
			toggleArm = true;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(Robot.gathererarm.getSetpoint() - Robot.gathererarm.getPosition()) < 0.1;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
