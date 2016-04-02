package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

public class CollectBallToggle extends Command {
	private CollectBall cb = new CollectBall();

	@Override
	protected void initialize() {
		if (cb.run) {
			cb.run = false;
		} else {
			Scheduler.getInstance().add(cb);
		}
	}

	@Override
	protected void execute() {}
	@Override
	protected boolean isFinished() {return true;}
	@Override
	protected void end() {}
	@Override
	protected void interrupted() {this.end();}
}
