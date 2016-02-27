package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class NoSwerveArcadeDrive extends Command {
    public NoSwerveArcadeDrive() {
    	requires(Robot.driveTrain);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.driveTrain.driveVoltage(Robot.oi.leftY(),Robot.oi.leftY());
    }
    
    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {
    	Robot.driveTrain.driveVoltage(0,0);
    }
}
