package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterAngleHigh extends Command {
/** puts the shooter to the highest angle it can be.
 */
    public ShooterAngleHigh() {
    	requires(Robot.shooterPivot);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.shooterPivot.setSetpoint(RobotMap.Voltages.shooterPivotGround);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}