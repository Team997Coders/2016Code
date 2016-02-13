package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BellTankDrive extends Command {

    public BellTankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double l = Robot.oi.lefty();
    	double r = Robot.oi.righty();
    	double lp = l*l;
    	double rp = r*r;
    	if (l < 0) l = -lp;
    	else l = lp;
    	if (r < 0) r = -rp;
    	else r = rp;
    	Robot.drivetrain.driveVoltage(l,r);
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
    // this is saying if something weird happens or gets interrupted to return to zero meaning don't drive into a wall and stop moving 
    protected void interrupted() {
    	Robot.drivetrain.driveVoltage(0,0);
    }
}
