package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.OI;
import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GatherTriggerNoSP extends Command {

    public GatherTriggerNoSP() {
    	requires(Robot.gathererArm);
    }

    @Override
	protected void initialize() {}

    @Override
	protected void execute() {
    	Robot.gathererArm.safeVoltage(OI.deadBand(Robot.oi.getRawTriggerAxis()));
    }

    @Override
	protected boolean isFinished() {
        return false;
    }

    @Override
	protected void end() {}

    @Override
	protected void interrupted() {}
}
