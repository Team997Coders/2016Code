package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shooter Pivot Angle Subsystem:
 * 
 * Control the angle of the shooter.  The shooter moves between end-points that sre set
 * for gathering balls from the floor to the high point against the upper portion of
 * the robot set for the high position limit.
 * 
 * Since the mechanical design of the shooter pivot placed the sensor on the off side, as the angle
 * increases up toward the robot the value returned by the sensor goes down.  Depending on the
 * mechanical position of the wiper will set the actual mapping of the wiper position and the 
 * shooter pivot position.
 * 
 * Note that currently the wiper is over rotated in the pivot and now as the arm rotates up the 
 * sensor value goes past the low value of 0.2V and it wraps around to the high value of 4.8V.
 */
public class ShooterPivot extends PIDSubsystem {
	private Talon pivotMotor;
	private Potentiometer shootAngle;
	private static final double absoluteTolerance = 0.01;

	public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
		// should the 'P' be negative?? Yes - since the sensor value goes down as angle goes up
		super("shooterPivot", -3.5, 0.0, -0.3);
		getPIDController().setContinuous(false);
		setInputRange(RobotMap.Voltages.shooterPivotRobot, RobotMap.Voltages.shooterPivotGround);
		setOutputRange(-0.5, 0.5);
		setAbsoluteTolerance(absoluteTolerance);
		setPercentTolerance(5.0);

		pivotMotor = new Talon(aimingMotorPort);
		shootAngle = new AnalogPotentiometer(shooterAnglePort);

		LiveWindow.addActuator("ShooterPivot", "ShooterAngleMotor", (Talon) pivotMotor);
		LiveWindow.addSensor("ShooterPivot", "ShooterAngleSensor", (AnalogPotentiometer) shootAngle);
		LiveWindow.addActuator("ShooterPivot", "ShooterPositionController", getPIDController());

		setSetpoint(RobotMap.Voltages.shooterPivotMiddleLow);
//		lockArmPosition();
		enable();
	}

	
//	public boolean onTarget() {
//		return Math.abs(getPosition() - getSetpoint()) < absoluteTolerance;
//	}

	@Override
	protected double returnPIDInput() {
		double angle = shootAngle.get();
		
		if (angle < 0.5) {
			angle += 0.91;  //if angle too low, increase it
		}
		return angle;
	}

	@Override
	protected void usePIDOutput(double output) {
		pivotMotor.pidWrite(output);
	}

	@Override
	public void initDefaultCommand() {}

	public void lockShootPosition() {
		setSetpoint(shootAngle.get());
		System.out.println("Shooter Angle Position Locked at" + shootAngle.get());
		enable();
	}

	public void safeVoltage(double voltage) {
		double angle = shootAngle.get();
		// gatherer arm goes from low=5.5 to high=2.3 in reverse
		// we don't want the arm going higher than 2.3 or lower than 5.5
		// positive voltage makes the arm go up.
		if (angle > RobotMap.Voltages.shooterPivotGround && voltage > 0) {
			// arm is down and we don't want it to go lower
			lockShootPosition();
		} else if (angle < RobotMap.Voltages.shooterPivotRobot && voltage < 0) {
			// arm is up and we don't want it going any further back
			lockShootPosition();
		} else {
			pivotMotor.set(voltage);
		}
	}

	public void smartDashboard() {
		SmartDashboard.putBoolean("Shooter Pivot Enabled?", getPIDController().isEnabled());
		SmartDashboard.putNumber("Shooter Pivot Setpoint: ",  super.getSetpoint());
		SmartDashboard.putNumber("Shooter Pivot Position: ",  super.getPosition());
		SmartDashboard.putNumber("Shooter Pivot Feedback Pot Voltage", shootAngle.get());
		SmartDashboard.putNumber("Shooter Pivot Error Term", getPIDController().getError());
		SmartDashboard.putBoolean("Shooter Pivot On Target?", super.onTarget());
		
	}
}