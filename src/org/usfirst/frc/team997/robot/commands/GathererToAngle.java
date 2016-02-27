package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GathererToAngle extends Command {
	private double angle;

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