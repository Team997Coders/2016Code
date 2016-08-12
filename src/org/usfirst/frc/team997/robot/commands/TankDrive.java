package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

    public TankDrive() {
    	requires(Robot.driveTrain);
    }

    @Override
	protected void initialize() {
    	ShooterToAngle.middleHigh();
    }

    @Override
	protected void execute() {
    	Robot.driveTrain.driveVoltage(Robot.oi.leftY(), Robot.oi.rightY());
    }

    @Override
	protected boolean isFinished() {
        return false;
    }

    @Override
	protected void end() {}

    @Override
	protected void interrupted() {
    	Robot.driveTrain.driveVoltage(0,0);
    }
}
