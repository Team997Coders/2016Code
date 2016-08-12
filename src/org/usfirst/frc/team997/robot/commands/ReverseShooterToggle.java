package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ReverseShooterToggle extends Command {
	private ReverseShooter rg = new ReverseShooter();

    protected void initialize() {
    	if (rg.run) {
    		rg.run = false;
    	} else {
			Scheduler.getInstance().add(rg);
		}
    }

    protected void execute() {}
    protected boolean isFinished() {return true;}
    protected void end() {}
    protected void interrupted() {this.end();}
}
