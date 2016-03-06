package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class GathererToAngle extends Command {
	public static final GathererToAngle
		armAngleLow = new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitGround),
		armAngleHigh = new GathererToAngle(RobotMap.Voltages.gathererArmBeforeHitRobot);

	private final double angle;

    public GathererToAngle(double angle) {
    	this.angle = angle;
    	requires(Robot.gathererArm);
    }

    protected void initialize() {
        Robot.gathererArm.setSetpoint(angle);
        Robot.gathererArm.enable();
    }

    protected void execute() {
        Robot.gathererArm.setSetpoint(angle);
    }

    protected boolean isFinished() {
        return Robot.gathererArm.onTarget();
    }

    protected void end() {}

    protected void interrupted() {
        this.end();
    }
}