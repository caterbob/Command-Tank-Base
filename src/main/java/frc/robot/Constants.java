package frc.robot;

public class Constants {

    // CAN IDs
    public static final int BACK_LEFT_ID = 1;
    public static final int BACK_RIGHT_ID = 3;
    public static final int FRONT_LEFT_ID = 4;
    public static final int FRONT_RIGHT_ID = 2;

    // Inversions
    public static final boolean BACK_LEFT_INVERT = false;
    public static final boolean BACK_RIGHT_INVERT = true;
    public static final boolean FRONT_LEFT_INVERT = false;
    public static final boolean FRONT_RIGHT_INVERT = true;

    // Control Constants
    public static final double MAX_SPEED_PERCENT = .33;
    public static final double JOYSTICK_DEADBAND = .2;

    // Distance between left and right (track width)
    public static final double TRACK_WIDTH = 1;

    // Encoder important
    public static final int COUNTS_PER_REV = 42;
    public static final double GEAR_RATIO = 1;
    public static final double WHEEL_DIAMETER = .1;

}