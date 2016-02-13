package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NoSwerveArcadeDrive extends Command {

    public NoSwerveArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveVoltage(Robot.oi.lefty(),Robot.oi.lefty());
    }
    
    private double deadBand(double a) {
    	if(Math.abs(a)>0.15) {
    		return a;
    	} else {
    		return 0;
    	}
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
    	Robot.drivetrain.driveVoltage(0,0);
    }
    
    
}
