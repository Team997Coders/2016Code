package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LearnShift extends Command {
	private double current;  // current pivot position
	private boolean direction; // which direction are we going to move?

    public LearnShift(boolean dir) {
    	// up = true, down = false
    	this.direction = dir;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooterPivot);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	current = Robot.shooterPivot.getPosition();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// we are not learning... just drop through
    	if (RobotMap.learnMode == false) {
    		this.end();
    	}
    	
    	// get the current position of the shooter pivot
    	current = Robot.shooterPivot.getPosition();

    	// check limits
    	if (current >= RobotMap.Voltages.shooterPivotRobot || current <= RobotMap.Voltages.shooterPivotGround) {
    		return;
    	}

    	// compute the middle position between the top and bottom of the shooter pivot positions
    	double midpoint = (RobotMap.Voltages.shooterPivotGround + (RobotMap.Voltages.shooterPivotRobot - RobotMap.Voltages.shooterPivotGround)/2.0);

    	// should we re-define the direction flag?  I wish java had the binary option like '?' in perl.
    	double incdir = 1.0;
    	if (this.direction == false ) {
    		incdir = -1.0;
    	}
    	
    	// this will store the new setpoint
    	double newSetpoint =  current + incdir * RobotMap.InitVoltages.shooterShiftInc;
    	
    		// shift up.  Use high setpoint if we are above the middle point, else se the lower setpoint
   		if (current < midpoint) { 
   			RobotMap.Voltages.shooterPivotMiddleLow = newSetpoint;
   		}
   		else if (current > midpoint) {
   			RobotMap.Voltages.shooterPivotMiddleHigh = newSetpoint;
   		}

   		// update the setpoint while I am at it
	    Robot.shooterPivot.setSetpoint(newSetpoint);
    	
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
    	this.end();
    }
}
