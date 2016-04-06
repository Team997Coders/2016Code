package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SpinUpShooter extends Command {
	static boolean globalSpinUp = false;

	/**
	 * this will be a toggle button that spins up the wheels and stops them based on when its pressed.
	 */
	
	public SpinUpShooter() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		if (globalSpinUp) {
			Robot.shooter.speedUp();
		} else {
			Robot.shooter.stop();
		}
	
		//Robot.shooter.speedUp();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.shooter.stop();
	}

	@Override
	protected void interrupted() {
		Robot.shooter.stop();
	}
}