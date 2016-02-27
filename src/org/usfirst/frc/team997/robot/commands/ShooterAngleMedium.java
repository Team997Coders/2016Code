package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterAngleMedium extends Command {
/**this command sets the shooter at the medium or half way angle
 */
    public ShooterAngleMedium() {
    	requires(Robot.shooterPivot);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.shooterPivot.setSetpoint(RobotMap.Voltages.shooterPivotMiddle);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}    
}
