package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriverTwoJoystickCommandPressed extends Command {
    public DriverTwoJoystickCommandPressed() {}
    protected void initialize() {}
    protected void execute() { DriverTwoJoystickCommand.on = true; }
    protected boolean isFinished() { return false; }
    protected void end() {}
    protected void interrupted() {}
}
