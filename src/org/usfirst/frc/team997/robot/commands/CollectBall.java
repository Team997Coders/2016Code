package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollectBall extends Command {
	/** this command will lower and start the gatherer, and shooter to have them 
	 *  gather in until the ball is in the shooter 
	 * 
	 */
    public CollectBall() {
    	requires(Robot.shooter);
    	requires(Robot.gathererArm);
    	requires(Robot.gatherer);
    }

    protected void initialize() {}

    protected void execute() {
    	//makes sure kicker is retracted before gathering
    	Robot.shooter.retractKicker();
    	
    	//calls the gathering method which gathers ball as long as sensor is not activated
    	Robot.shooter.gatherBall();
    	
    	//sets arm to lowest position
    	Robot.gathererArm.setSetpoint(RobotMap.Voltages.gathererArmBeforeHitGround);
    	
    	//makes the wheel on the end of the gatherer arm roll the ball in
    	Robot.gatherer.gathervoltage(RobotMap.gathererInSpeed);
    }

    protected boolean isFinished() {
    	//stops when the shooter sensor detects the ball
        return Robot.shooter.getshooterSensor();
    }

    protected void end() {
    	Robot.shooter.slowDown();
    	Robot.gatherer.safeValue(0);
    }

    protected void interrupted() {}
    
}

