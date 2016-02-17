package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//leftstick moves up  than motors  dance on forward 
    	//leftstick moves down than motors fall and die backwards
    	//rightstick moves to the left left motor tapdances back and right motor dies forward 
    	//rightstick moves to the right leftmotor spazzes  forward and right motor sings backwords
    	//leftstick  and right are falling all the way forward and left - left motor will be full speed and rightmotor at slowish speed
    	//leftstick and right are falling all the way forward and right - leftmotor will be slowish and right motor at full speed
    	 double getArcadeleftspeed
    	= deadBand(Robot.oi.lefty() - Robot.oi.rightx());

    	 double getArcaderightspeed
    	 = deadBand(Robot.oi.lefty() + Robot.oi.rightx());

    	 SmartDashboard.putNumber("Arcade Left", getArcadeleftspeed);
    	 SmartDashboard.putNumber("Arcade Right", getArcaderightspeed);
    	Robot.drivetrain.driveVoltage(getArcadeleftspeed,getArcaderightspeed);
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
