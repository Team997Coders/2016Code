package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MidShooterLowGather extends CommandGroup {
    
    public  MidShooterLowGather() {
    	addParallel(new ShooterAngleMedium());
    	addParallel(new ArmAngleLow());
    }
}
