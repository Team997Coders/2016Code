package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team997.robot.RobotMap;


public class RobotPrefs {
	public static void readPrefs() {
		// Gatherer Arm Position
		RobotMap.Voltages.gathererArmBeforeHitGround = Robot.prefs.getDouble("gathererArmBeforeHitGround", RobotMap.InitVoltages.gathererArmBeforeHitGround);
		RobotMap.Voltages.gathererArmBeforeHitRobot = Robot.prefs.getDouble("gathererArmBeforeHitRobot", RobotMap.InitVoltages.gathererArmBeforeHitRobot);
		RobotMap.Voltages.collectArmPostion = Robot.prefs.getDouble("collectArmPosition", RobotMap.InitVoltages.collectArmPostion);
		
		// Shooter Pivot Positions
		RobotMap.Voltages.shooterPivotGround = Robot.prefs.getDouble("shooterPivotGround", RobotMap.InitVoltages.shooterPivotGround);
		RobotMap.Voltages.shooterPivotMiddleLow = Robot.prefs.getDouble("shooterPivotMiddleLow", RobotMap.InitVoltages.shooterPivotMiddleLow);
		RobotMap.Voltages.shooterPivotMiddleHigh = Robot.prefs.getDouble("shooterPivotMiddleHigh", RobotMap.InitVoltages.shooterPivotMiddleHigh);
		RobotMap.Voltages.shooterPivotRobot = Robot.prefs.getDouble("shooterPivotRobot", RobotMap.InitVoltages.shooterPivotRobot);
	}

	public static void writePrefs() {
		// Gatherer Arm Position
		Robot.prefs.putDouble("gathererArmBeforeHitGround", RobotMap.Voltages.gathererArmBeforeHitGround);
		Robot.prefs.putDouble("gathererArmBeforeHitRobot", RobotMap.Voltages.gathererArmBeforeHitRobot);
		Robot.prefs.putDouble("collectArmPosition", RobotMap.Voltages.collectArmPostion);
		
		// Shooter Pivot Positions
		Robot.prefs.getDouble("shooterPivotGround", RobotMap.Voltages.shooterPivotGround);
		Robot.prefs.getDouble("shooterPivotMiddleLow", RobotMap.Voltages.shooterPivotMiddleLow);
		Robot.prefs.getDouble("shooterPivotMiddleHigh", RobotMap.Voltages.shooterPivotMiddleHigh);
		Robot.prefs.getDouble("shooterPivotRobot", RobotMap.Voltages.shooterPivotRobot);
	}

}
