
package org.usfirst.frc.team997.robot;

import org.usfirst.frc.team997.robot.commands.ExampleCommand;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.Gatherer;
import org.usfirst.frc.team997.robot.subsystems.GathererArm;
import org.usfirst.frc.team997.robot.subsystems.Shooter;
import org.usfirst.frc.team997.robot.subsystems.ShooterPivot;

import com.analog.adis16448.frc.ADIS16448_IMU;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	//private CameraServer camera;
	public static final Shooter shooter = new Shooter(
			RobotMap.shooterBallSensor, RobotMap.shooterMotorPort,
			RobotMap.servoMotorFirstPort, RobotMap.servoMotorSecondPort);
	public static final ShooterPivot shooterpivot = new ShooterPivot(RobotMap.aimingMotorPort, RobotMap.shooterAnglePort);
	public static final DriveTrain drivetrain = 
			new DriveTrain(RobotMap.leftMotorPort, RobotMap.rightMotorPort,
			               RobotMap.leftEncoderFirstPort, RobotMap.leftEncoderSecondPort, 
			               RobotMap.rightEncoderFirstPort, RobotMap.rightEncoderSecondPort, 
			               RobotMap.maxAccelDrive);
	public static final Gatherer gatherer = new Gatherer(RobotMap.rollerMotorPort);
	public static final GathererArm gathererarm = new GathererArm(RobotMap.gatherArmMotorPort, RobotMap.armAnglePort);
	public static OI oi;

	public static ADIS16448_IMU imu;

    private Command autonomousCommand;
    private SendableChooser chooser;

   //public static Compressor compressor;
    public static PowerDistributionPanel pdp;
    

    public void robotInit() {
		oi = new OI();
		
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
        
//        chooser.addObject("My Auto", new MyAutoCommand());
        imu = new ADIS16448_IMU();
        pdp = new PowerDistributionPanel();
        SmartDashboard.putData("Auto mode", chooser);
        /*
         
        camera = CameraServer.getInstance();
        camera.setQuality(42);
        camera.startAutomaticCapture("cam0");
        
        clight = new Relay(RobotMap.circleLightPort);
        */
        imu.calibrate();
        
        // Need to reset the servo's position to be ready to capture a ball
        Robot.shooter.retractKicker();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		/* String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	Robot.gathererarm.lockArmPosition();
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
    	
        Scheduler.getInstance().run();
        Smartdashboard();
       
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    
public void Smartdashboard(){
	Robot.drivetrain.smartDashboard();
	 Robot.shooter.smartDashboard();
	 Robot.gathererarm.smartdashboardupdate();
	 Robot.shooterpivot.smartDashboard();
	 SmartDashboard.putData("Imu", imu);
	 SmartDashboard.putNumber("Imu angle", imu.getAngleY());
	 
	 //imu info put on the smartdashboard
	 SmartDashboard.putNumber("imu rate X",imu.getRateX());
     SmartDashboard.putNumber("imu angle X", imu.getAngleX());
     SmartDashboard.putNumber("imu rate Y", imu.getRateY());
     SmartDashboard.putNumber("imu angle Y", imu.getAngleY());
     SmartDashboard.putNumber("imu rate Z", imu.getRateZ());
     SmartDashboard.putNumber("imu angle Z", imu.getAngle());
     SmartDashboard.putNumber("roll angle", imu.getRoll());
	 
	 
}


}
