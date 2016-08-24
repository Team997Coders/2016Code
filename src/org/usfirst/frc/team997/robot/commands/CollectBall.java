package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectBall extends Command {
	boolean run = false;
	/** this command will lower and start the gatherer, and shooter to have them 
	 *  gather in until the ball is in the shooter 
	 * 
	 */
    public CollectBall() {
    	requires(Robot.shooter);
    	requires(Robot.gathererArm);
    	requires(Robot.gatherer);
    }

    @Override
	protected void initialize() {run = true;}

    @Override
	protected void execute() {
    	//makes sure kicker is retracted before gathering
    	Robot.shooter.retractKicker();
    	
    	//calls the gathering method which gathers ball as long as sensor is not activated
    	Robot.shooter.gatherBall();
    	
    	//sets arm to lowest position
    	Robot.gathererArm.setSetpoint(RobotMap.Voltages.collectArmPostion);
    	
    	//sets shooter to lowest position
    	Robot.shooterPivot.setSetpoint(RobotMap.Voltages.shooterPivotGround);
    	
    	//makes the wheel on the end of the gatherer arm roll the ball in
    	Robot.gatherer.safeValue(RobotMap.gathererInSpeed);
    }

    @Override
	protected boolean isFinished() {
    	//stops when the shooter sensor detects the ball
        return !run || Robot.shooter.isBallCollected();
    }

    @Override
	protected void end() {
    	run = false;
    	Robot.shooter.stop();
    	Robot.gatherer.safeValue(0);
    }

    @Override
	protected void interrupted() {
    	this.end();
    }
    
}

