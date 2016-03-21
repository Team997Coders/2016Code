package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveToSetpoint extends Command {
	private final double setpoint, speed;
	
    public DriveToSetpoint(double speed, double distance) {
    	requires(Robot.driveTrain);
    	this.setpoint = distance;
    	this.speed = speed;
    }
    
    // set up a default of half speed.  This might still
    // be too fast.
    public DriveToSetpoint(double distance) {
        this(0.5, distance);
    }

    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	Robot.driveTrain.resetEncoders();
    	Robot.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
     	final double threshold = 0.1;
     	int sign = (int) Math.signum(this.setpoint); //-1, 0, 1 depending on sign of the input

    	//final double Kp = 0.03;
    	
    	//double angle = Robot.gyro.getAngle(); // get current heading
        //double adjust = Kp*angle;
    	double adjust = Robot.driveTrain.getDeltaEncoderRate() / 10;
        
    	// ensure doesn't drastically swerve from readings.
    	if (adjust > threshold) adjust = threshold;
    	else if (adjust < -threshold) adjust = -threshold;
    	
    	SmartDashboard.putNumber("Gyro Drive Straight Adjustment: ", adjust);

    	Robot.driveTrain.driveVoltage(sign*(this.speed - adjust), sign*(this.speed + adjust));
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return Robot.driveTrain.getAverageEncoderDistance() > setpoint;
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	Robot.driveTrain.driveVoltage(0, 0);
    }    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	Robot.driveTrain.driveVoltage(0, 0);
    }
}
