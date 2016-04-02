package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

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

    protected void end() {
    	Shooter.globalSpinUp = 0;
    	Robot.shooter.stop();
    	Robot.shooter.retractKicker();
    	timer.stop();
    }

    @Override
    protected void interrupted() {
    	Robot.shooter.stop();
    }
}
