package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class HighShooterLowGather extends CommandGroup {
    public  HighShooterLowGather() {
    	//Sets shooter to a high angle and gatherer arm to a low angle
    	addParallel(new ShooterAngleHigh());
    	addParallel(new ArmAngleLow());
    }
}
