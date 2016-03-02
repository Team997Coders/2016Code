package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverTwoJoystickCommandReleased extends Command {
    public DriverTwoJoystickCommandReleased() {}
    protected void initialize() {}
    protected void execute() { DriverTwoJoystickCommand.on = false; }
    protected boolean isFinished() { return false; }
    protected void end() {}
    protected void interrupted() {}
}
