package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GathererToAngle extends Command {
	private double m_angle;

    public GathererToAngle(double angle) {
    	this.m_angle = angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.gathererarm);
    }

 // RobotBuilder Version: 2.0
    //
    // This file was generated by RobotBuilder. It contains sections of
    // code that are automatically generated and assigned by robotbuilder.
    // These sections will be updated in the future when you export to
    // Java from RobotBuilder. Do not put any code or make any change in
    // the blocks indicating autogenerated code or it will be lost on an
    // update. Deleting the comments indicating the section will prevent
    // it from being updated in the future.


    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.gathererarm.setSetpoint(m_angle);
        Robot.gathererarm.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.gathererarm.setSetpoint(m_angle);
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
        this.end();
    }
}