package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveBackwards extends CommandGroup {
    public AutoDriveBackwards() {
        //Use this auto routine for the low bar.
        addParallel(ShooterToAngle.high()); //actually makes shooter low
        addParallel(GathererToAngle.low());
        addSequential(new DriveToSetpointBackwards(-100));
    }
}
