package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.OI;
import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class GatherTriggerNoSP extends Command {

    public GatherTriggerNoSP() {
    	requires(Robot.gathererArm);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.gathererArm.safeVoltage(OI.deadBand(Robot.oi.getRawTriggerAxis()));
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}
