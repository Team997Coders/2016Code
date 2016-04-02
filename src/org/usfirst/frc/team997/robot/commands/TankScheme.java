package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Controller;

public class TankScheme implements ControlScheme {
	public double getLeft(Controller c) {
		return c.getLeftRawY();
	}

	public double getRight(Controller c) {
		return c.getRightRawY();
	}
}
