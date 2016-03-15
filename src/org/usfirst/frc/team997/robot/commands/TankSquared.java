package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankSquared extends Command {

    public TankSquared() {
    	requires(Robot.driveTrain);
    }

    @Override
	protected void initialize() {}

    @Override
	protected void execute() {
    	
    	double leftSq = Robot.oi.leftY()*Robot.oi.leftY();
    	double rightSq = Robot.oi.rightY()*Robot.oi.rightY();
    	
    	if (leftSq < 0) {
    		leftSq = -leftSq;
    	}
    	
    	if (rightSq < 0) {
    		rightSq = -rightSq;
    	}
    	
    	Robot.driveTrain.driveVoltage(leftSq, rightSq);
    }

    @Override
	protected boolean isFinished() {
        return false;
    }

    @Override
	protected void end() {}

    @Override
	protected void interrupted() {}
}
