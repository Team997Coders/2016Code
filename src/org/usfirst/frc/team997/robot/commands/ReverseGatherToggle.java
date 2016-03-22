package org.usfirst.frc.team997.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class ReverseGatherToggle extends Command {
	private ReverseGather rg = new ReverseGather();

   @Override
    protected void initialize() {
    	if(rg.run) {
    		rg.run = false;
    	} else {
    		Scheduler.getInstance().add(rg);
    	}
    }

   @Override
    protected void execute() {}
    
    @Override
    protected boolean isFinished() {return true;}

    @Override
    protected void end() {}
    
    @Override
    protected void interrupted() {this.end();}
   
}
