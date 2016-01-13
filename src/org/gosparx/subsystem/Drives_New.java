package org.gosparx.subsystem;

import org.gosparx.IO;

import edu.wpi.first.wpilibj.Talon;

public class Drives_New extends GenericSubsystem{

	private Talon leftFront;
	private Talon leftRear;
	private Talon rightFront;
	private Talon rightRear;
	private double wantedLeft;
	private double wantedRight;
	private static Drives_New drives_new;
	
	public static Drives_New getInstance(){
		if(drives_new == null){
			drives_new = new Drives_New();
		}
		return drives_new;
	}
	
	private Drives_New() {
		super("Drives_New", Thread.NORM_PRIORITY);
	}

	@Override
	protected boolean init() {
		leftFront = new Talon(IO.LEFT_FRONT_DRIVES_PWM);
		leftRear = new Talon(IO.LEFT_REAR_DRIVES_PWM);
		rightFront = new Talon(IO.RIGHT_FRONT_DRIVES_PWM);
		rightRear = new Talon(IO.RIGHT_REAR_DRIVES_PWM);
		return true;
	}

	@Override
	protected void liveWindow() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean execute() {
		leftFront.set(wantedLeft);
		leftRear.set(wantedLeft);
		rightFront.set(wantedRight);
		rightRear.set(wantedRight);
		return false;
	}

	@Override
	protected long sleepTime() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	protected void writeLog() {
		// TODO Auto-generated method stub
		
	}
	
	public void setSpeed(double left, double right){
		wantedLeft = left;
		wantedRight = right;
	}

}
