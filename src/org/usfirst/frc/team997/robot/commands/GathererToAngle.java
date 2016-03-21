package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GathererToAngle extends Command {
	public static GathererToAngle low() { return new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround); }
	public static GathererToAngle high() { return new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot); }
	public static GathererToAngle mid() {return new GathererToAngle(RobotMap.Voltages.gathererArmMid); }
	public static GathererToAngle collect() {return new GathererToAngle(RobotMap.Voltages.collectArmPostion); }

	private final double angle;

    public GathererToAngle(double angle) {
    	this.angle = angle;
    	requires(Robot.gathererArm);
    }

    @Override
	protected void initialize() {
        Robot.gathererArm.setSetpoint(angle);
        Robot.gathererArm.enable();
    }

    @Override
	protected void execute() {
        Robot.gathererArm.setSetpoint(angle);
    }

    @Override
	protected boolean isFinished() {
        return Robot.gathererArm.onTarget();
    }

    @Override
	protected void end() {}

    @Override
	protected void interrupted() {
        this.end();
    }
}