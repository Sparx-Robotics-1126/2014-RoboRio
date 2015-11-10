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
	private Drives drives;

	//********************************************************************
	//*******************Driver Controller Mapping************************
	//********************************************************************
	private static final int NEW_JOY_Y_AXIS = 1;
	private static final int NEW_JOY_X_AXIS = 0;
	private static final int NEW_JOY_TRIGGER = 1;//TRIGGEr
	private static final int NEW_JOY_LEFT = 2;//LEFT
	private static final int NEW_JOY_RIGHT = 3;//RIGHT
	private static final int NEW_JOY_MIDDLE = 4;

	//***************************************************************************
	//***************************XBOX360*****************************************
	//***************************************************************************
	private static final int XBOX_A = 1;
	private static final int XBOX_B = 2;
	private static final int XBOX_X = 3;
	private static final int XBOX_Y = 4;
	private static final int XBOX_L1 = 5;
	private static final int XBOX_R1 = 6;
	private static final int XBOX_BACK = 7;
	private static final int XBOX_START = 8;
	private static final int XBOX_L3 = 9;
	private static final int XBOX_R3 = 10;
	private static final int XBOX_LEFT_X = 0;
	private static final int XBOX_LEFT_Y = 1;
	private static final int XBOX_L2 = 2;
	private static final int XBOX_R2 = 3;
	private static final int XBOX_RIGHT_X = 4;
	private static final int XBOX_RIGHT_Y = 5;
	private static final int XBOX_POV = 0;


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
		drives = Drives.getInstance();
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
		drives.setSpeed(driverLeftJoy.getAxis(NEW_JOY_Y_AXIS), driverRightJoy.getAxis(NEW_JOY_Y_AXIS));
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
