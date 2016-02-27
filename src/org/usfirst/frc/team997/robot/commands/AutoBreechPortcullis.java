package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *  This is a 'special' that was requested by the design team.  The question
 *  was 'is it possible to handle moving through the portcullis autonomously?'
 *  
 *  1 - If we know the portcullis is in front of us.  
 *  		--> Driver action from a button or Auto placement
 *  2 - Move the tusks down to portcullis lift position and shooter
 *      moves to a position slightly above the floor position to be out
 *      of the way going over the ramp. (ShooterRampAmgle)
 *  		--> standard Gather2Angle('bottom')
 *  3 - Drive straight forward until we are engaged with portcullis
 *  		... this is the tricky one.
 *  		--> ultrasonic move to distance
 *  		--> infrared move till distance
 *  4 - Lift gate and...
 *  5 - Move forward as gate goes up
 *  		--> standard gate move (maybe more power...)
 *  		--> move straight forward at 0.5 speed
 *  		--> will need to tune the speed to get from under the
 *  		--> 	portcullis in time.
 *  6 - stop move and lower gatherer
 *  
 */

public class AutoBreechPortcullis extends CommandGroup {
    public AutoBreechPortcullis() {
    	addParallel(new ShooterAngleLow());
    	addSequential(new ArmAngleLow());
    	addSequential(new ShooterToAngle(RobotMap.shooterRampAngle));
    }
}
