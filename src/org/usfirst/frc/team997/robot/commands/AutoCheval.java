package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCheval extends CommandGroup {
    public AutoCheval() {
    	addParallel(ShooterToAngle.middleLow());
    	addParallel(GathererToAngle.mid());
    	addSequential(new DriveToSetpoint(24)); //2ft
    	addParallel(GathererToAngle.low());
    	addSequential(new DriveToSetpoint(60));
    }
}
