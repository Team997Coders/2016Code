package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveBackwards extends CommandGroup {
    
    public  AutoDriveBackwards() {
        addParallel(ShooterToAngle.low);
        addSequential(GathererToAngle.low);
        addSequential(new DriveToSetpointBackwards(-100));
    }
}
