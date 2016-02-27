package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ArmAngleLow extends Command {
    public ArmAngleLow() {
    	requires(Robot.gathererArm);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.gathererArm.setSetpoint(RobotMap.Voltages.gathererArmBeforeHitGround);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
