package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * All it does is flip the shooter's globalSpinUp.
 */

public class SpinUpShooterToggle extends Command {
    public SpinUpShooterToggle() {}

    protected void initialize() {
    	Shooter.globalSpinUp++;
    	
    	if(Shooter.globalSpinUp > 2) {
    		Shooter.globalSpinUp = 0;
    	}
    }
    
    protected void execute() {
    	if(Robot.shooter.globalSpinUp == 2) {
    		Robot.shooter.speedUp();
    	} else if(Robot.shooter.globalSpinUp == 1) {
    		Robot.shooter.speedSlow();
    	} else {
    		Robot.shooter.stop();
    	}
    }
    
    protected boolean isFinished() {return true;}
    
    protected void end() {}
    
    protected void interrupted() {}
}
