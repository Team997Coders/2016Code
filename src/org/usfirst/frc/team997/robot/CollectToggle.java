package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.command.Command;

public class CollectToggle extends Command {
	public boolean on = false;

	protected void initialize() {
		
	}

	protected void execute() {
		on = !on;
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		
	}

	protected void interrupted() {
		
	}
}
