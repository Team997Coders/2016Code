package org.usfirst.frc.team997.robot.commands;

import org.usfirst.frc.team997.robot.Controller;

public interface ControlScheme {
	double getLeft(Controller c);
	double getRight(Controller c);
}
