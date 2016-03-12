package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterToAngle extends Command {
	public static ShooterToAngle high() { return new ShooterToAngle(RobotMap.Voltages.shooterPivotGround); }
	public static ShooterToAngle low() { return new ShooterToAngle(RobotMap.Voltages.shooterPivotRobot); }
	public static ShooterToAngle middleLow() { return new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddleLow); }
	public static ShooterToAngle middleHigh() { return new ShooterToAngle(RobotMap.Voltages.shooterPivotMiddleHigh); }

	private double angle;

    public ShooterToAngle(double angle) {
    	this.angle = angle;
    	requires(Robot.shooterPivot);
    }

    protected void initialize() {
        Robot.shooterPivot.setSetpoint(angle);
        Robot.shooterPivot.enable();
    }

    protected void execute() {
    	Robot.shooterPivot.setSetpoint(angle);
    }

    protected boolean isFinished() {
    	return Robot.shooterPivot.onTarget();
    }

    protected void end() {}

    protected void interrupted() {
        this.end();
    }
}