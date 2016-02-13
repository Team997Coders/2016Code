package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class circleLightToggle extends Command {
	private boolean  clightToggle;
    public circleLightToggle() {
    	 clightToggle = true;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	lightToggle();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
    
    public void lightToggle() {
    	if (clightToggle){
    		Robot.clight.set(Relay.Value.kForward);
    		clightToggle = false;
    	}else{
    		Robot.clight.set(Relay.Value.kOff);
    		clightToggle = true;
    	}
    }
}
