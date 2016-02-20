package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUpShooter extends Command {
	private static boolean toggleSpin = true;
	private boolean myToggleSpin;

	/**
	 * this will be a toggle button that spins up the wheels and stops them based on when its pressed.
	 */
	public SpinUpShooter() {
		requires(Robot.shooter);
	}

	protected void initialize() {
		myToggleSpin = toggleSpin;
		toggleSpin = !toggleSpin;
	}

	protected void execute() {
		if (myToggleSpin) {
			Robot.shooter.speedUp();
		} else {
			Robot.shooter.slowDown();
		}
	}

	protected boolean isFinished() {
		return !myToggleSpin || myToggleSpin == toggleSpin;
	}

	protected void end() {
		Robot.shooter.slowDown();
	}

	protected void interrupted() {
		Robot.shooter.slowDown();
	}
}
