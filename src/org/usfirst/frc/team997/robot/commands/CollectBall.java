package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectBall extends Command {
	/** this command will lower and start the gatherer, and shooter to have them gather in until the ball is in the shooter 
	 *  (aka the beam break says its there)
	 * TODO change it so then it does ^.
	 * 
	 */
    public CollectBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.shooter);
    	requires(Robot.gathererarm);
    	requires(Robot.gatherer);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//makes sure kicker is retracted before gathering
    	Robot.shooter.retractKicker();
    	
    	//calls the gathering method which gathers ball as long as sensor is not activated
    	Robot.shooter.gatherBall();
    	
    	//sets arm to lowest position
    	Robot.gathererarm.setSetpoint(RobotMap.collectBallArmPos);
    	
    	//makes the wheel on the end of the gatherer arm roll the ball in
    	Robot.gatherer.gathervoltage(RobotMap.gathererInSpeed);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//stops when the shooter sensor detects the ball
        return Robot.shooter.getshooterSensor();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.slowDown();
    	Robot.gatherer.safeValue(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    
    }
}

// people who read unnecessary comments are cool
//so are people who make unnecessary comments :)
