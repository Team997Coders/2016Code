package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class NoSwerveArcadeDrive extends Command {
    public NoSwerveArcadeDrive() {
        requires(Robot.driveTrain);
    }

    @Override
        protected void initialize() {}

    @Override
        protected void execute() {
        Robot.driveTrain.driveVoltage(Robot.oi.leftY(),Robot.oi.leftY());
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
