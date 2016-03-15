package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToSetpointBackwards extends Command {
	private final double setpoint, speed;

    public DriveToSetpointBackwards(double speed, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.setpoint = distance;
    	this.speed = speed;
    }

    //set default speed
    public DriveToSetpointBackwards(double distance) {
    	this(0.5, distance);
    }
    
    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	//makes sure we can trust the encoder values
    	Robot.driveTrain.resetEncoders();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	
    	double adjust = Robot.driveTrain.getDeltaEncoderRate() / 10;
    	final double threshold = .1;
    	
    	// ensure doesn't drastically swerve from readings.
    	if (adjust > threshold) adjust = threshold;
    	else if (adjust < -threshold) adjust = -threshold;
    	
    	SmartDashboard.putNumber("Gyro Drive Straight Adjustment: ", adjust);

    	Robot.driveTrain.driveVoltage(-(this.speed - adjust), -(this.speed + adjust));
    }


    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return Robot.driveTrain.getAverageEncoderDistance() < setpoint;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	Robot.driveTrain.driveVoltage(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	Robot.driveTrain.driveVoltage(0, 0);
    }
}
