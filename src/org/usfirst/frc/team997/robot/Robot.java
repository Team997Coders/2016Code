
package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.AutoBreechForward;
import org.usfirst.frc.team997.robot.commands.AutoBrickWall;
import org.usfirst.frc.team997.robot.commands.AutoCheval;
import org.usfirst.frc.team997.robot.commands.AutoDriveBackwards;
import org.usfirst.frc.team997.robot.commands.AutoDriveForward;
import org.usfirst.frc.team997.robot.commands.NullCommand;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Gatherer;
import org.usfirst.frc.team997.robot.subsystems.GathererArm;
import org.usfirst.frc.team997.robot.subsystems.Shooter;
import org.usfirst.frc.team997.robot.subsystems.ShooterPivot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private CameraServer camera;
	public static final Shooter shooter = new Shooter(RobotMap.shooterBallSensor, RobotMap.shooterMotorPort,
			RobotMap.servoMotorFirstPort, RobotMap.servoMotorSecondPort);
	public static final ShooterPivot shooterPivot = new ShooterPivot(RobotMap.aimingMotorPort,
			RobotMap.shooterPivotAnglePort);
	public static final DriveTrain driveTrain = new DriveTrain(RobotMap.leftMotorPort, RobotMap.rightMotorPort,
			RobotMap.leftEncoderFirstPort, RobotMap.leftEncoderSecondPort, RobotMap.rightEncoderFirstPort,
			RobotMap.rightEncoderSecondPort);
	public static final Gatherer gatherer = new Gatherer(RobotMap.rollerMotorPort);
	public static final GathererArm gathererArm = new GathererArm(RobotMap.gatherArmMotorPort,
			RobotMap.gathererArmAnglePort);
	public static ADXRS450_Gyro gyro;

	public static OI oi;

	public Command autonomousCommand;
	private SendableChooser chooser;

	public static PowerDistributionPanel pdp;

	@Override
	public void robotInit() {
		oi = new OI();

		chooser = new SendableChooser();
		chooser.addDefault("Forward", new AutoDriveForward());
		chooser.addObject("Backward", new AutoDriveBackwards()); // use this for the low bar
		chooser.addObject("Cheval", new AutoCheval());
		chooser.addObject("Nothing", new NullCommand());
		chooser.addObject("BreechForward", new AutoBreechForward());
		chooser.addObject("BrickWall", new AutoBrickWall());

		SmartDashboard.putData("Auto mode", chooser);

		gyro = new ADXRS450_Gyro();
		gyro.calibrate();

		pdp = new PowerDistributionPanel();

		camera = CameraServer.getInstance();
		camera.setQuality(42);
		camera.startAutomaticCapture("cam1");

		// Need to reset the servo's position to be ready to capture a ball.
		// Retracts kicker servos.
		Robot.shooter.retractKicker();

		autonomousCommand = new AutoCheval();
	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		smartDashboard();
	}

	@Override
	public void autonomousInit() {
		gyro.reset();
		Robot.driveTrain.resetEncoders();
		if (getSelected() != null) {
			getSelected().start();
		}
	}

	// private Command getSelected() { return autonomousCommand; }
	private Command getSelected() {
		return (Command) chooser.getSelected();
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		smartDashboard();
	}

	@Override
	public void teleopInit() {
		Robot.gathererArm.lockArmPosition();
		//include lockShootPosition() for shooterpivot?
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		smartDashboard();
	}

	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}

	public void smartDashboard() {
		Robot.driveTrain.smartDashboard();
		Robot.shooter.smartDashboard();
		Robot.gathererArm.smartDashboard();
		Robot.shooterPivot.smartDashboard();

		// imu info put on the smartdashboard
		SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
		SmartDashboard.putNumber("Gyro Rate", Robot.gyro.getRate());
	}

}
