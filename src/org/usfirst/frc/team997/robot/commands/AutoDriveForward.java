package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoDriveForward extends CommandGroup {
    public AutoDriveForward() {
    	addParallel(ShooterToAngle.shooterAngleLow);
    	addSequential(GathererToAngle.armAngleLow);
    	addSequential(new DriveToSetpoint(100));
    }
}
