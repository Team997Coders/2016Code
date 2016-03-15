
package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.AutoCheval;
import org.usfirst.frc.team997.robot.commands.AutoDriveBackwards;
import org.usfirst.frc.team997.robot.commands.AutoDriveForward;
import org.usfirst.frc.team997.robot.commands.NullCommand;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Gatherer;
import org.usfirst.frc.team997.robot.subsystems.GathererArm;
//import org.usfirst.frc.team997.robot.subsystems.GathererArmNoSP;
import org.usfirst.frc.team997.robot.subsystems.Shooter;
import org.usfirst.frc.team997.robot.subsystems.ShooterPivot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
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
	public static Relay clight;
	private CameraServer camera;
	public static final Shooter shooter = new Shooter(
			RobotMap.shooterBallSensor, RobotMap.shooterMotorPort,
			RobotMap.servoMotorFirstPort, RobotMap.servoMotorSecondPort);
	public static final ShooterPivot shooterPivot = new ShooterPivot(
			RobotMap.aimingMotorPort, RobotMap.shooterPivotAnglePort);
	public static final DriveTrain driveTrain = 
			new DriveTrain(RobotMap.leftMotorPort, RobotMap.rightMotorPort,
			               RobotMap.leftEncoderFirstPort, RobotMap.leftEncoderSecondPort, 
			               RobotMap.rightEncoderFirstPort, RobotMap.rightEncoderSecondPort);
	public static final Gatherer gatherer = new Gatherer(RobotMap.rollerMotorPort);
	public static final GathererArm gathererArm = 
			new GathererArm(RobotMap.gatherArmMotorPort, RobotMap.gathererArmAnglePort);
	//public static final GathererArmNoSP gathererArm = new GathererArmNoSP(RobotMap.gatherArmMotorPort);
	
	public Preferences prefs;
	
	public static ADXRS450_Gyro gyro;

	public static OI oi;

	public Command autonomousCommand;
	private SendableChooser chooser;

	public static PowerDistributionPanel pdp;

	public void robotInit() {
		oi = new OI();
		
		readPrefs();

		chooser = new SendableChooser();
		chooser.addObject("Forward", new AutoDriveForward());
		chooser.addDefault("Backward", new AutoDriveBackwards()); //use this for low bar
		chooser.addObject("Cheval", new AutoCheval());
		chooser.addObject("Nothing", new NullCommand());

		SmartDashboard.putData("Auto mode", chooser);
		
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();  // Try not to access the gyro or move the robot during the calibration
							// One reference says that this could take 5 seconds to complete
		
		pdp = new PowerDistributionPanel();

        camera = CameraServer.getInstance();
        camera.setQuality(42);
        camera.startAutomaticCapture("cam1");        
		Robot.shooter.retractKicker();
		
		autonomousCommand = new AutoCheval();
		
		RobotMap.learnMode = false;
	}

	/*
	 * These preferences are saved to the flash memory on the RoboRio and are persistant across reboot cycles.  We need to
	 * add these values to the prefs dialog in the smart dashboard.
	 */
	private void readPrefs() {
		// Gatherer Arm Positions
		RobotMap.Voltages.gathererArmBeforeHitGround = prefs.getDouble("gathererArmBeforeHitGround", RobotMap.InitVoltages.gathererArmBeforeHitGround);
		RobotMap.Voltages.gathererArmBeforeHitRobot = prefs.getDouble("gathererArmBeforeHitRobot", RobotMap.InitVoltages.gathererArmBeforeHitRobot);
		RobotMap.Voltages.collectArmPostion = prefs.getDouble("collectArmPosition", RobotMap.InitVoltages.collectArmPostion);
		
		// Shooter Pivot Positions
		RobotMap.Voltages.shooterPivotGround = prefs.getDouble("shooterPivotGround", RobotMap.InitVoltages.shooterPivotGround);
		RobotMap.Voltages.shooterPivotMiddleLow = prefs.getDouble("shooterPivotMiddleLow", RobotMap.InitVoltages.shooterPivotMiddleLow);
		RobotMap.Voltages.shooterPivotMiddleHigh = prefs.getDouble("shooterPivotMiddleHigh", RobotMap.InitVoltages.shooterPivotMiddleHigh);
		RobotMap.Voltages.shooterPivotRobot = prefs.getDouble("shooterPivotRobot", RobotMap.InitVoltages.shooterPivotRobot);
	}
	
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		gyro.reset();
		Robot.driveTrain.resetEncoders();
		getSelected().start();
	}
	
	private Command getSelected() { return (Command) chooser.getSelected(); }

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		smartDashboard();
	}

	public void teleopInit() {
		Robot.gathererArm.lockArmPosition();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		smartDashboard();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

	public void smartDashboard(){
		Robot.driveTrain.smartDashboard();
		Robot.shooter.smartDashboard();
		Robot.gathererArm.smartDashboard();
		Robot.shooterPivot.smartDashboard();
		
		//imu info put on the smartdashboard
		SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
		SmartDashboard.putNumber("Gyro Rate", Robot.gyro.getRate());
	}

}
