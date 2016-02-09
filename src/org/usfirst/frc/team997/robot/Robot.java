
package org.usfirst.frc.team997.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team997.robot.commands.ExampleCommand;
import org.usfirst.frc.team997.robot.subsystems.DriveTrain;
import org.usfirst.frc.team997.robot.subsystems.ExampleSubsystem;
import org.usfirst.frc.team997.robot.subsystems.Shooter;

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

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static final Shooter shooter = new Shooter(RobotMap.rightEncoderSecondPort, RobotMap.shooterMotorPort, RobotMap.servoMotorPort, RobotMap.shooterEncoderFirstPort);
	public static final DriveTrain drivetrain = new DriveTrain(RobotMap.leftMotorPort, RobotMap.rightMotorPort);
	public static OI oi;

    private Command autonomousCommand;
    private SendableChooser chooser;

    public static Compressor compressor;
    public static PowerDistributionPanel pdp;
    public static Gatherer gatherer;
    public static DriveTrain driveTrain;

    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());

        compressor = new Compressor();
        pdp = new PowerDistributionPanel();
        gatherer = new Gatherer();
       // driveTrain = new DriveTrain();
        SmartDashboard.putData("Auto mode", chooser);
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
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        Robot.shooter.smartDashboard();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
