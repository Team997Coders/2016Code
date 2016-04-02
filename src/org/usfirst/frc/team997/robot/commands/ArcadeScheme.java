package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Controller;

public class ArcadeScheme implements ControlScheme {
	public double getLeft(Controller c) {
		return c.getLeftRawY() - c.getRightRawX();
	}

	public double getRight(Controller c) {
		return c.getLeftRawY() + c.getRightRawX();
	}
}
