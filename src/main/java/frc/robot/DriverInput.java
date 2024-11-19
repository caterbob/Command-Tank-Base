package frc.robot;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;

public class DriverInput {

    private final XboxController xboxController;

    public DriverInput() {
        xboxController = new XboxController(0);
    }

    public double driveX() {
        // this axis is inverted because up on the joystick is negative by default. Changed to be positive
        double output =
                -Math.signum(xboxController.getLeftY()) * Math.pow(xboxController.getLeftY(), 2);
        return output;
    }

    public double driveY() {
        double output =
                Math.signum(xboxController.getRightX()) * Math.pow(xboxController.getRightX(), 2);
        return output;
    }

}