package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {
    public AutoDriveForward() {
    	addSequential(new LogOnInitBoolean("AutoDriveForward running", true));

    	// needs in parallel as they don't finish for some reason
    	addParallel(ShooterToAngle.middleLow());
    	addParallel(GathererToAngle.mid());
    	addSequential(new DriveToSetpoint(100));

    	addSequential(new LogOnInitBoolean("AutoDriveForward running", false));
    }
}
