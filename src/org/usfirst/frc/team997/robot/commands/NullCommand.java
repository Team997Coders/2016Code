package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class NullCommand extends Command {
    @Override
        protected void initialize() {}
    @Override
        protected void execute() {}

    @Override
        protected boolean isFinished() {
        return true;
    }

    @Override
        protected void end() {}
    @Override
        protected void interrupted() {}
}
