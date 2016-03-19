package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LearnShift extends Command {
	private boolean isDirectionUp; // which direction are we going to move?

    public LearnShift(boolean dir) {
    	// up = true, down = false
    	this.isDirectionUp = dir;
    	
    	requires(Robot.shooterPivot);
    }

    protected void initialize() {}

    protected void execute() {
    	// we are not learning... just drop through
    	if (!RobotMap.learnMode) {
    		return;
    	}
    	
    	double current = Robot.shooterPivot.getPosition();  // current pivot position

    	// check limits
    	if (current >= RobotMap.Voltages.shooterPivotRobot || current <= RobotMap.Voltages.shooterPivotGround) {
    		return;
    	}

    	// compute the middle position between the top and bottom of the shooter pivot positions
    	double midpoint = (RobotMap.Voltages.shooterPivotGround + RobotMap.Voltages.shooterPivotRobot) / 2.0;

    	// should we re-define the direction flag?  I wish java had the binary option like '?' in perl.
    	double incdir = 1.0;
    	if (!this.isDirectionUp) {
    		incdir = -incdir;
    	}

    	// this will store the new setpoint
    	double newSetpoint = current + incdir * RobotMap.InitVoltages.shooterShiftInc;
    	
    	// shift up.  Use high setpoint if we are above the middle point, else set the lower setpoint
   		if (current < midpoint) { 
   			RobotMap.Voltages.shooterPivotMiddleLow = newSetpoint;
   		} else if (current > midpoint) {
   			RobotMap.Voltages.shooterPivotMiddleHigh = newSetpoint;
   		}

   		// update the setpoint while I am at it
	    Robot.shooterPivot.setSetpoint(newSetpoint);
    }

    protected boolean isFinished() { return true; }
    protected void end() {}
    protected void interrupted() { this.end(); }
}
