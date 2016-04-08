package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *USED FOR PORTCULLIS IGNORE THE "PORTCULLIS" COMMAND IT IS A LIE!!!!
 */
public class AutoBreechForward extends CommandGroup {
    
    public  AutoBreechForward() {
    	addSequential(new LogOnInitBoolean("AutoBreechForward running", true));

    	// needs in parallel as they don't finish for some reason
    	addParallel(ShooterToAngle.middleHigh());
    	addParallel(GathererToAngle.low());
    	addSequential(new DriveToSetpoint(75));

    	addSequential(new LogOnInitBoolean("AutoBreechForward running", false));
    }
}
