package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ReverseShooter extends Command {
	boolean run = false;

    public ReverseShooter() {
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {run = true;}
   

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.speedUp();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !run;
    }

    // Called once after isFinished returns true
    protected void end() {
    	run = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	this.end();
    }
}
