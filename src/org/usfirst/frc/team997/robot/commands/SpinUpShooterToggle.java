package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * All it does is flip SpinUpShooter's globalSpinUp.
 */
public class SpinUpShooterToggle extends Command {
    public SpinUpShooterToggle() {}
    protected void initialize() {
    	SpinUpShooter.globalSpinUp = !SpinUpShooter.globalSpinUp;
    }
    protected void execute() {}
    protected boolean isFinished() {return true;}
    protected void end() {}
    protected void interrupted() {}
}
