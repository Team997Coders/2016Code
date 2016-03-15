package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * All it does is flip SpinUpShooter's globalSpinUp.
 */

public class SpinUpShooterToggle extends Command {
    public SpinUpShooterToggle() {}

    @Override
	protected void initialize() {
    	SpinUpShooter.globalSpinUp = !SpinUpShooter.globalSpinUp;
    }
    
    @Override
	protected void execute() {}
    
    @Override
	protected boolean isFinished() {return true;}
    
    @Override
	protected void end() {}
    
    @Override
	protected void interrupted() {}
}
