package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * All it does is flip the shooter's globalSpinUp.
 */

public class SpinUpShooterToggle extends Command {
    public SpinUpShooterToggle() {}

    @Override
    protected void initialize() {
    	Shooter.globalSpinUp++;
    	
    	if(Shooter.globalSpinUp > 2) {
    		Shooter.globalSpinUp = 0;
    	}
    }
    
    @Override
    protected void execute() {
    	if(Shooter.globalSpinUp == 2) {
    		Robot.shooter.speedUp();
    	} else if(Shooter.globalSpinUp == 1) {
    		Robot.shooter.speedSlow();
    	} else {
    		Robot.shooter.stop();
    	}
    }
    
    @Override
	protected boolean isFinished() {return true;}
    
    @Override
	protected void end() {}
    
    @Override
	protected void interrupted() {}
}
