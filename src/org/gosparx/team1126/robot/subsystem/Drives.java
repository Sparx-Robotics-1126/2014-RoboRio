package org.gosparx.team1126.robot.subsystem;

import org.gosparx.team1126.robot.IO;

import edu.wpi.first.wpilibj.Talon;

public class Drives extends GenericSubsystem{

	private double wantedLeft;
	private double wantedRight;
	
	private Talon leftFront;
	private Talon leftBack;
	private Talon rightFront;
	private Talon rightBack;
	
	private static Drives drives;
	
	public static Drives getInstance(){
		if(drives == null){
			drives = new Drives();
		}
		return drives;
	}
	
	private Drives() {
		super("Drives", Thread.NORM_PRIORITY);
	}

	@Override
	protected boolean init() {
		leftFront = new Talon(IO.PWM_DRIVES_LEFT_FRONT);
		leftBack = new Talon(IO.PWM_DRIVES_LEFT_BACK);
		rightFront = new Talon(IO.PWM_DRIVES_RIGHT_FRONT);
		rightBack = new Talon(IO.PWM_DRIVES_RIGHT_BACK);
		return true;
	}

	@Override
	protected void liveWindow() {
		
	}

	@Override
	protected boolean execute() {
		leftFront.set(wantedLeft);
		leftBack.set(wantedLeft);
		rightFront.set(wantedRight);
		rightBack.set(wantedRight);
		return false;
	}

	@Override
	protected long sleepTime() {
		return 20;
	}

	@Override
	protected void writeLog() {

	}

	public void setSpeed(double left, double right){
		wantedLeft = left;
		wantedRight = right;
	}
}
