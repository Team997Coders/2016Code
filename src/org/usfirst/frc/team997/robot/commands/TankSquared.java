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

    protected void initialize() {}

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

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
