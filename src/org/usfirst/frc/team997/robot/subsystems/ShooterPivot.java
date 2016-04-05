package org.usfirst.frc.team997.robot.subsystems;

import org.usfirst.frc.team997.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Describe the system:  How the pivot moves with respect to the sensor values and the
 * motor voltages.
 */
public class ShooterPivot extends PIDSubsystem {
        private Talon pivotMotor;
        private AnalogPotentiometer shootAngle;
        private static final double absoluteTolerance = 0.01;

        public ShooterPivot(int aimingMotorPort, int shooterAnglePort) {
                // should the 'P' be negative??
                super("shooterPivot", -3.5, 0.0, -0.3);
                getPIDController().setContinuous(false);
                getPIDController().setInputRange(RobotMap.Voltages.shooterPivotRobot, RobotMap.Voltages.shooterPivotGround);
                getPIDController().setOutputRange(-0.5, 0.5);
                 getPIDController().setAbsoluteTolerance(absoluteTolerance);
                getPIDController().setPercentTolerance(5.0);

                pivotMotor = new Talon(aimingMotorPort);
                shootAngle = new AnalogPotentiometer(shooterAnglePort);

                LiveWindow.addActuator("ShooterPivot", "ShooterPositionController", getPIDController());
                LiveWindow.addActuator("ShooterPivot", "ShooterAngleMotor", pivotMotor);
                LiveWindow.addSensor("ShooterPivot", "ShooterAngleSensor", shootAngle);

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
                if (angle < .5) {
                        angle += 0.91; 
                }
                return angle;
        }

        @Override
        protected void usePIDOutput(double output) {
                pivotMotor.pidWrite(output);
        }

        @Override
        public void initDefaultCommand() {}

        public void lockArmPosition() {
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
                        lockArmPosition();
                } else if (angle < RobotMap.Voltages.shooterPivotRobot && voltage < 0) {
                        // arm is up and we don't want it going any further back
                        lockArmPosition();
                } else {
                        pivotMotor.set(voltage);
                }
        }

        public void smartDashboard() {
                SmartDashboard.putBoolean("Shooter Pivot PID Status", getPIDController().isEnabled());
                SmartDashboard.putNumber("Shooter Pivot Setpoint: ", super.getSetpoint());
                SmartDashboard.putNumber("Shooter Pivot Position: ", super.getPosition());
                SmartDashboard.putNumber("Shooter Pivot Feedback Pot Voltage", shootAngle.get());
                SmartDashboard.putNumber("Shooter Pivot Error Term", getPIDController().getError());
                SmartDashboard.putBoolean("Shooter Pivot On Target?", super.onTarget());
                
        }
}
