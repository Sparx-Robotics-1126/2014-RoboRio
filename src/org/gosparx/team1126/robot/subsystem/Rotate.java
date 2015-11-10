package org.gosparx.team1126.robot.subsystem;

import org.gosparx.team1126.robot.IO;
import org.gosparx.team1126.robot.sensors.EncoderData;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Rotate extends GenericSubsystem{

	private static Rotate rotate;
	private Solenoid rotateLock;
	private Solenoid shortCyl;
	private Solenoid longCyl;
	private Talon rotateMotor;
	private Talon acqMotor;
	private Encoder rotateEnc;
	private EncoderData rotateEncData;
	private DigitalInput topLimit;
	private DigitalInput bottomLimit;
	private double wantedAngle;
	private double wantedAcqSpeed; 
	private State currentState;
	private final double DIST_PER_TICK = 0.14064698;

	public static Rotate getInstance(){
		if(rotate == null){
			rotate = new Rotate();
		}
		return rotate;
	}

	private Rotate() {
		super("Rotate", Thread.NORM_PRIORITY);
	}

	@Override
	protected boolean init() {
		rotateLock = new Solenoid(IO.PNU_ROTATE_LOCK);
		rotateMotor = new Talon(IO.PWM_ROTATE_MOTOR);
		rotateEnc = new Encoder(IO.DIO_ROTATE_ENC_A, IO.DIO_ROTATE_ENC_B);
		rotateEncData = new EncoderData(rotateEnc, DIST_PER_TICK);
		shortCyl = new Solenoid(IO.PNU_ACQ_SHORT);
		longCyl = new Solenoid(IO.PNU_ACQ_LONG);
		acqMotor = new Talon(IO.PWM_ACQ_MOTOR);
		topLimit = new DigitalInput(IO.DIO_ROTATE_TOP);
		bottomLimit = new DigitalInput(IO.DIO_ROTATE_BOTTOM);
		currentState = State.STANDBY;
		return true;
	}

	@Override
	protected void liveWindow() {

	}

	@Override
	protected boolean execute() {
		double wantedRotateSpeed = 0, wantedAcqSpeed = 0;
		rotateEncData.calculateSpeed();
		switch(currentState){
		case STANDBY:
			break;
		case ROTATE:
			wantedRotateSpeed = (wantedAngle - rotateEncData.getDistance())/50;
			if(Math.abs(wantedAngle - rotateEncData.getDistance()) < 1.5){
				if(!rotateLock.get())
					rotateLock.set(true);
			}else if(Math.abs(wantedRotateSpeed) < .25){
				if(rotateLock.get())
					rotateLock.set(false);
				wantedRotateSpeed = (wantedRotateSpeed < 0)? -.25: .25;
			}
			break;
		case ACQUIRING:
			if(bottomLimit.get()){
				wantedRotateSpeed = -.25;
			}else{
				if(!longCyl.get())
					longCyl.set(true);
				if(!shortCyl.get())
					shortCyl.set(true);
				wantedAcqSpeed = .7;
			}
			break;
		}
		rotateMotor.set(wantedRotateSpeed);
		acqMotor.set(wantedAcqSpeed);
		return false;
	}

	@Override
	protected long sleepTime() {
		return 20;
	}

	@Override
	protected void writeLog() {

	}

	public void setAngle(double angle){
		wantedAngle = angle;
		currentState = State.ROTATE;
	}
	
	public void acquire(){
		currentState = State.ACQUIRING;
	}

	private enum State{
		ROTATE,
		STANDBY,
		ACQUIRING;
	}
}
