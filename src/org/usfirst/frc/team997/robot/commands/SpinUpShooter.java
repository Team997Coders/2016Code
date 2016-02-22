package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUpShooter extends Command {
	static boolean globalSpinUp = true;

	/**
	 * this will be a toggle button that spins up the wheels and stops them based on when its pressed.
	 */
	public SpinUpShooter() {
		requires(Robot.shooter);
	}

	protected void initialize() {
	}

	protected void execute() {
		if (globalSpinUp) {
			Robot.shooter.speedUp();
		} else {
			Robot.shooter.slowDown();
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.shooter.slowDown();
	}

	protected void interrupted() {
		Robot.shooter.slowDown();
	}
}
