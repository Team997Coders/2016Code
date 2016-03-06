package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowShooterHighGather extends CommandGroup {
    
    public LowShooterHighGather() {
    	//Sets the shooter to a low angle while setting gatherer arm to a high angle
    	addParallel(ShooterToAngle.shooterAngleLow);
    	addParallel(GathererToAngle.armAngleHigh);
    }
}
