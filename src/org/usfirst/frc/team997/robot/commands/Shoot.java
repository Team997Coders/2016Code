package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Shoot extends Command {
    private Timer timer;
    public Shoot() {
    	//using the stuff from the shooter class to do things
    	requires(Robot.shooter);
    	//creating a timer for the shooter
    	timer = new Timer();
    }

    @Override
	protected void initialize() {
    	//resetting the timer in case of weirdness
    	timer.reset();
    	//starting the timer for the shooter to operate
    	timer.start();
    }

    @Override
	protected void execute() {
    	Robot.shooter.kickKicker();
    }

    @Override
	protected boolean isFinished() {
        return timer.get() > 4;
    }

    @Override
	protected void end() {
    	SpinUpShooter.globalSpinUp = false;
    	Robot.shooter.slowDown();
    	Robot.shooter.retractKicker();
    	timer.stop();
    }

    @Override
	protected void interrupted() {
    	Robot.shooter.slowDown();
    }
}
