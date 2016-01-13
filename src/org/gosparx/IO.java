package org.gosparx;

/**
 * The purpose of this class is to have all the electrical configuration in one 
 * place.
 *
 * @author Justin Bassett (Bassett.JustinT@gmail.com)
 */
public class IO {
	
    // Drives PWM Slots
    public static final int LEFT_FRONT_DRIVES_PWM       = 3;
    public static final int LEFT_REAR_DRIVES_PWM        = 4;
    public static final int RIGHT_FRONT_DRIVES_PWM      = 6;
    public static final int RIGHT_REAR_DRIVES_PWM       = 7;
    
    //Drives Gyro slot
    //public static final int GYRO_ANALOG                 = 1;
    
    //Drives Booleans
    //public static final boolean DRIVES_TURN_LEFT        = true;
    //public static final boolean DRIVES_TURN_RIGHT       = false;
   
    //public static final int PRESSURE_SWITCH_CHAN        = 14;
    //public static final int COMPRESSOR_RELAY_CHAN       = 1;
    
    // Controls IO Ports
        
    public static final int LEFT_DRIVER_JOY_PORT        = 0;
    
    public static final int RIGHT_DRIVER_JOY_PORT       = 1;
    
    public static final int OPER_JOY_PORT               = 2;
    
    //Analog Ports
    //public static final int DEFAULT_ANALOG_MODULE       = 1;
    //public static final int AUTOSWITCH_CHANNEL          = 2;
    
    //Shooter IO
    public static final int WINCH_POT_CHAN              = 3;
    
    //public static final int CAN_ADRESS_PIVOT            = 4;
    public static final int PWM_PIVOT                   = 2;
    
    
    public static final int PWM_LEFT_WINCH                 = 0;
    
    //public static final int CAN_ADRESS_WINCH            = 2;
    public static final int PWM_RIGHT_WINCH                   = 5;
    
    //public static final int CAN_ADRESS_ACQ              = 3;
    public static final int PWM_ACQ                     = 1;
    
    public static final int PIVOT_ENCODER_CHAN_1        = 0;
    
    public static final int PIVOT_ENCODER_CHAN_2        = 1;
    
    public static final int ACQ_TOGGLE_CHAN             = 1;
    
    public static final int ACQ_BALL_DETECTOR           = 5;
    
    public static final int LATCH_LIMIT_SWITCH_CHAN     = 4;
    
    public static final int SHOOTER_ACQ_MODE_CHAN       = 3;
    
    public static final int SHOOTER_SAFE_MODE_CHAN      = 2;
    
    public static final int LATCH_CHAN                  = 3;
    
    public static final int KEEP_IN_FRAME_CHAN          = 2;
    
    //public static final int CAMERA_LIGHT_RELAY          = 8;
    
    //public static final int PNU_TENSION                 = 5;
    
    public static final int PNU_BRAKE                   = 5;
}
