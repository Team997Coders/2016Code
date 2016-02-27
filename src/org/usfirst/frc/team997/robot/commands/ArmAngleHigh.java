package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ArmAngleHigh extends Command {
    public ArmAngleHigh() {
    	requires(Robot.gathererArm);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.gathererArm.setSetpoint(RobotMap.Voltages.gathererArmBeforeHitRobot);
    }

    protected boolean isFinished() {
    	return Robot.gathererArm.onTarget();
    }

    protected void end() {}

    protected void interrupted() {}
}