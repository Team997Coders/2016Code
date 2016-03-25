package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBreechForward extends CommandGroup {
    
    public  AutoBreechForward() {
        // Add Commands here:
    	addSequential(new LogOnInitBoolean("AutoBreechForward running", true));

    	// needs in parallel as they don't finish for some reason
    	addParallel(ShooterToAngle.middleHigh());
    	addParallel(GathererToAngle.mid());
    	addSequential(new DriveToSetpoint(200));

    	addSequential(new LogOnInitBoolean("AutoBreechForward running", false));
    }
}
