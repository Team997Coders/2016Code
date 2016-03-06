package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveBackwards extends CommandGroup {
    
    public  AutoDriveBackwards() {
        addParallel(ShooterToAngle.shooterAngleLow);
        addSequential(GathererToAngle.armAngleLow);
        addSequential(new DriveToSetpointBackwards(-100));
    }
}
