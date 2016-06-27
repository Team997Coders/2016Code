package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBrickWall extends CommandGroup {
    public AutoBrickWall() {
        addSequential(new LogOnInitBoolean("AutoBrickWall running", true));

        //Use this auto routine for the brick wall.
        addParallel(ShooterToAngle.low()); //makes shooter high
        addParallel(GathererToAngle.low());
        addSequential(new DriveToSetpointBackwards(.5, -165)); 
        addSequential(new LogOnInitBoolean("AutoBrickWall running", false));
    }
}