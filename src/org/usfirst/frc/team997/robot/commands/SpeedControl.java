package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.DoubleReference;
import org.usfirst.frc.team997.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SpeedControl extends Command {
	private static final double maxAccel = .015; //decreased from 0.025 for safe mode by Julia on 8-5-16
	
	private double lastLeft = 0, lastRight = 0;
	
	private ControlScheme controlMap = new TankScheme();

    public SpeedControl() {
    	requires(Robot.driveTrain);
    }
    
    private double algorithm(final double current, final DoubleReference last, final String side) {
    	boolean maxedOut = true;
    	double ret = current;
    	if (current == 0) {
    		last.value = 0;
    		maxedOut = false;
    	} else if (current - last.value > maxAccel) {
    		last.value += maxAccel;
    		ret = last.value;
    	} else if (last.value - current > maxAccel) {
    		last.value -= maxAccel;
    		ret = last.value;
    	} else {
    		if ((last.value < 0 && current > 0) || (last.value > 0 && current < 0)) {
    			maxedOut = true;
    		} else {
    			maxedOut = false;
    		}
    		last.value = current;
    	}
		SmartDashboard.putBoolean("Max out " + side, maxedOut);
    	return ret;
    }

    protected void initialize() {}
    protected void execute() {
    	double left = controlMap.getLeft(Robot.oi.driverOne);
    	double right = controlMap.getRight(Robot.oi.driverOne);

    	SmartDashboard.putNumber("Input Left", left);
    	SmartDashboard.putNumber("Input Right", right);

    	DoubleReference lastL = new DoubleReference(lastLeft);
    	left = algorithm(left, lastL, "Left");
    	lastLeft = lastL.value;
    	DoubleReference lastR = new DoubleReference(lastRight);
    	right = algorithm(right, lastR, "Right");
    	lastRight = lastR.value;

    	Robot.driveTrain.driveVoltage(left, right);
    }
    protected boolean isFinished() {return false;}
    protected void end() {}
    protected void interrupted() {}
}
