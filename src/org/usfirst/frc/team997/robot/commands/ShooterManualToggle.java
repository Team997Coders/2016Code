package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterManualToggle extends Command {
	private final boolean isManuallyControlled;

	public ShooterManualToggle(boolean isManuallyControlled) {
    	this.isManuallyControlled = isManuallyControlled;
    }

    protected void initialize() {
    	ShooterManual.isManuallyControlled = isManuallyControlled;
    }

    protected void execute() {}
    protected boolean isFinished() {return false;}
    protected void end() {}
    protected void interrupted() {}
}
