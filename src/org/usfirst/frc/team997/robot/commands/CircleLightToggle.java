package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;

public class CircleLightToggle extends Command {
	private boolean  clightToggle;
    public CircleLightToggle() {
    	 clightToggle = true;
    }

    protected void initialize() {}

    protected void execute() {
    	lightToggle();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {}

    protected void interrupted() {}
    
    public void lightToggle() {
    	if (clightToggle) {
    		Robot.clight.set(Relay.Value.kForward);
    	} else {
    		Robot.clight.set(Relay.Value.kOff);
    	} 
    	clightToggle = !clightToggle;
    }
}
