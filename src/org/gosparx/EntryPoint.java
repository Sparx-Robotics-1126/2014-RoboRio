package org.gosparx;

//import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.gosparx.subsystem.Acquisitions;
import org.gosparx.subsystem.Controls_New;
import org.gosparx.subsystem.Drives_New;
import org.gosparx.subsystem.GenericSubsystem;
import org.gosparx.subsystem.Shooter;
import org.gosparx.util.LogWriter;
import org.gosparx.util.Logger;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class EntryPoint extends SampleRobot {
	/**
	 * The list of all the subsystems.
	 */
	private GenericSubsystem[] subsystems;
	private Logger logger;
	private Compressor compressor;
	//private CameraServer serv;

	/**
	 * Robot-wide initialization code should go here. Users should override this 
	 * method for default Robot-wide initialization which will be called when 
	 * the robot is first powered on. Called exactly 1 time when the competition 
	 * starts.
	 */
	public void robotInit(){
		logger = new Logger("Robot State");
		GenericSubsystem subsystems[][]= {
				{LogWriter.getInstance()},
				{Drives_New.getInstance()},
				{Controls_New.getInstance()},
				{Acquisitions.getInstance()},
				{Shooter.getInstance()},
		};
		compressor = new Compressor(0);
		logger = new Logger("Robot State");
		for (int i = 0; i < subsystems.length; i++) {
			subsystems[i][0].start();
		}
		logger.logMessage("Robot init ended");
		//serv = CameraServer.getInstance();
		//serv.setQuality(100);
		//serv.startAutomaticCapture("cam0");
	}

	/**
	 * This function is called once each time the robot enters autonomous mode.
	 */
	public void autonomous() {
	}

	/**
	 * This function is called once each time the robot enters operator control.
	 */
	public void operatorControl() {
		logger.logMessage("Switched to Teleop");
	}

	/**
	 * Disabled should go here. Users should overload this method to run code 
	 * that should run while the field is disabled. Called once each time the 
	 * robot enters the disabled state.
	 */
	public void disabled(){
		logger.logMessage("Switched to Disabled");
	}

	/**
	 * This function is called once each time the robot enters test mode.
	 */
	public void test() {
		while(isTest() && isEnabled()){
			LiveWindow.run();
			Timer.delay(0.1);
		}
	}

}
