package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Shoot extends Command {
    private Timer mytimer;
    public Shoot() {
        // Use requires() here to declare subsystem dependencies
        // e.g. requires(chassis);
    	
    	//using the stuff from the shooter class to do things
    	requires(Robot.shooter);
    	//creating a timer for the shooter
    	mytimer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//reseting the timer in case of weirdness
    	mytimer.reset();
    	//starting the timer for the shooter to operate
    	mytimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (mytimer.get() >= 1) {
    		Robot.shooter.kickKicker();
    	} else {
    		Robot.shooter.retractKicker();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return mytimer.get() > 3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.slowDown();
    	Robot.shooter.retractKicker();
    	mytimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.slowDown();
    }
}
