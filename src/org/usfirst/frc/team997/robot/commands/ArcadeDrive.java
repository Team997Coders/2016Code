package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ArcadeDrive extends Command {

    public ArcadeDrive() {
        requires(Robot.driveTrain);
    }

    @Override
        protected void initialize() {
    }

    @Override
        protected void execute() {
        // leftStick moves up  than motors dance on forward
        // leftStick moves down than motors go backwards
        // rightStick moves to the left leftMotor goes back and right motor goes forward
        // rightStick moves to the right leftMotor goes forward and right motor sings backwards
        // leftStick and right are going all the way forward and left - leftMotor will be full speed and rightMotor at slowish speed
        // leftStick and right are going all the way forward and right - leftMotor will be slowish and rightMotor at full speed

        double getArcadeLeftSpeed = deadBand(Robot.oi.leftY() - Robot.oi.rightX());
        double getArcadeRightSpeed = deadBand(Robot.oi.leftY() + Robot.oi.rightX());

         SmartDashboard.putNumber("Arcade Left", getArcadeLeftSpeed);
         SmartDashboard.putNumber("Arcade Right", getArcadeRightSpeed);
        Robot.driveTrain.driveVoltage(getArcadeLeftSpeed,getArcadeRightSpeed);
    }

    private double deadBand(double a) {
        if(Math.abs(a) > 0.15) {
                return a;
        } else {
                return 0;
        }
    }

    @Override
        protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
        protected void end() {
    }

    @Override
        protected void interrupted() {
        Robot.driveTrain.driveVoltage(0,0);
    }
}
