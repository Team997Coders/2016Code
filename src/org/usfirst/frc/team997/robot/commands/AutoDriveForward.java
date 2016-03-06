package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {
    public AutoDriveForward() {
    	addParallel(ShooterToAngle.low);
    	addSequential(GathererToAngle.low);
    	addSequential(new DriveToSetpoint(100));
    }
}
