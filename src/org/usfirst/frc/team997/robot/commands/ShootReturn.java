package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ShootReturn extends CommandGroup {
    
	//TODO test to see if we can use the Arm command in this command, make sure that you check to make sure that the arm only goes down and not up!
    /**this will take the shoot command, the Arm command and the shooterAngleLow command and shoot the ball out as well as return 
     * the gatherer and shooter to the lowest position  
     */
	
	public  ShootReturn() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
		addSequential(new Shoot());
		addParallel(new ArmAngleLow());
		addParallel(new ShooterAngleLow());
    }
}
