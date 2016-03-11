package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CollectBallToggle extends Command {
	private CollectBall cb = new CollectBall();

	protected void initialize() {
		if (cb.run) {
			cb.run = false;
		} else {
			Scheduler.getInstance().add(cb);
		}
	}

	protected void execute() {}
	protected boolean isFinished() {return true;}
	protected void end() {}
	protected void interrupted() {
		this.end();
	}
}
