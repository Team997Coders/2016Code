package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToSetpoint extends Command {
	double setpoint;
    public DriveToSetpoint(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.resetEncoders();
    	Robot.drivetrain.resetGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double adjust = Robot.drivetrain.getGyro() / 10;

    	final double threshold = .1;
    	// ensure doesn't drastically swerve from readings.
    	if (adjust > threshold) adjust = threshold;
    	else if (adjust < -threshold) adjust = -threshold;

    	Robot.drivetrain.drivevoltage(.5 - adjust, .5 + adjust);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drivetrain.getAverageEncoders() > setpoint;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drivevoltage(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.drivevoltage(0, 0);
    }
}
