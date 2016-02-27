package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterAngleLow extends Command {
/** this sets the shooter angle to the medium angle/halfway angle.
 */
	
    public ShooterAngleLow() {
    	requires(Robot.shooterPivot);
    }

    protected void initialize() {}
    
    protected void execute() {
    	Robot.shooterPivot.setSetpoint(RobotMap.Voltages.shooterPivotRobot);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}    
}
