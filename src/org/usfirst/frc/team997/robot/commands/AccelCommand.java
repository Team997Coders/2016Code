package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.subsystems.AccelMotor;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AccelCommand extends Command {
	private AccelMotor motor;

    public AccelCommand(AccelMotor motor) {
      	requires(motor);
    	this.motor = motor;
    }

    protected void execute() { motor.update(); }

    protected void initialize() {}
    protected boolean isFinished() { return false; }
    protected void end() {}
    protected void interrupted() { motor.stopMovingAbrupt(); }
}
