package org.gosparx.team1126.robot.subsystem;

import org.gosparx.team1126.robot.IO;
import org.gosparx.team1126.robot.util.AdvancedJoystick;
import org.gosparx.team1126.robot.util.AdvancedJoystick.ButtonEvent;
import org.gosparx.team1126.robot.util.AdvancedJoystick.JoystickListener;

public class Controls extends GenericSubsystem implements JoystickListener{

	private AdvancedJoystick operJoy;
	private AdvancedJoystick driverLeftJoy;
	private AdvancedJoystick driverRightJoy;
	private static Controls controls;
	
	public static Controls getInstance(){
		if(controls == null){
			controls = new Controls();
		}
		return controls;
	}
	
	private Controls() {
		super("Controls", Thread.NORM_PRIORITY);
	}

	@Override
	protected boolean init() {
		operJoy = new AdvancedJoystick("Operator", IO.OPER_JOY);
		driverLeftJoy = new AdvancedJoystick("Driver Left", IO.DRIVER_LEFT_JOY);
		driverRightJoy = new AdvancedJoystick("Driver Right", IO.DRIVER_RIGHT_JOY);
		return true;
	}

	@Override
	protected void liveWindow() {

	}

	@Override
	protected boolean execute() {
		
		return false;
	}

	@Override
	protected long sleepTime() {
		return 20;
	}

	@Override
	protected void writeLog() {

	}

	@Override
	public void actionPerformed(ButtonEvent e) {
		
	}

}
