package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUpShooter extends Command {
	private static int togglespin;
	/**
	 * this will be a toggle button that spins up the wheels and stops them based on when its pressed.
	 */
    public SpinUpShooter() {
    	togglespin = 0;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    if (togglespin == 0){
    	Robot.shooter.speedUp();
    	togglespin = 1;
    } else{
    	Robot.shooter.slowDown();
    	togglespin = 0;
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
    }
   

}
