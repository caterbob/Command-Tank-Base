package frc.robot;

// https://software-metadata.revrobotics.com/REVLib-2024.json

import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.function.Supplier;

public class TankDrive extends SubsystemBase {

    CANSparkMax frontRightMotor = new CANSparkMax(Constants.FRONT_RIGHT_ID, MotorType.kBrushless);
    CANSparkMax frontLeftMotor = new CANSparkMax(Constants.FRONT_LEFT_ID, MotorType.kBrushless);
    CANSparkMax backRightMotor = new CANSparkMax(Constants.BACK_RIGHT_ID, MotorType.kBrushless);
    CANSparkMax backLeftMotor = new CANSparkMax(Constants.BACK_LEFT_ID, MotorType.kBrushless);

    RelativeEncoder leftEncoder = frontLeftMotor.getEncoder();
    RelativeEncoder rightEncoder = frontRightMotor.getEncoder();

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Constants.TRACK_WIDTH);

  public TankDrive() {

    // Factory Defaults
    frontRightMotor.restoreFactoryDefaults();
    frontLeftMotor.restoreFactoryDefaults();
    backRightMotor.restoreFactoryDefaults();
    backLeftMotor.restoreFactoryDefaults();

    // Coast Mode
    frontRightMotor.setIdleMode(IdleMode.kBrake);
    frontLeftMotor.setIdleMode(IdleMode.kBrake);
    backRightMotor.setIdleMode(IdleMode.kBrake);
    backLeftMotor.setIdleMode(IdleMode.kBrake);

    // Inversions
    frontRightMotor.setInverted(Constants.FRONT_RIGHT_INVERT);
    frontLeftMotor.setInverted(Constants.FRONT_LEFT_INVERT);
    backRightMotor.setInverted(Constants.BACK_RIGHT_INVERT);
    backLeftMotor.setInverted(Constants.BACK_LEFT_INVERT);

    // Setting encoders to return m/s instead of RPM
    leftEncoder.setVelocityConversionFactor(Constants.GEAR_RATIO * Math.PI * Constants.WHEEL_DIAMETER / 60.0);
    rightEncoder.setVelocityConversionFactor(Constants.GEAR_RATIO * Math.PI * Constants.WHEEL_DIAMETER / 60.0);
    
  }

  public Command drive(Supplier<Double> X, Supplier<Double> Y) {

    return this.runOnce(
        () -> {
          double drive = X.get();
          drive = (Math.abs(drive) < Constants.JOYSTICK_DEADBAND) ? 0 : drive * Constants.MAX_SPEED_PERCENT;

          double turn = Y.get();
          turn = (Math.abs(turn) < Constants.JOYSTICK_DEADBAND) ? 0 : turn * Constants.MAX_SPEED_PERCENT;

          frontRightMotor.set(drive - turn);
          backRightMotor.set(drive - turn);
          frontLeftMotor.set(drive + turn);
          backLeftMotor.set(drive + turn);

        });
  }

  public double getLeftSpeed(){
    leftEncoder.getVelocity();
    return 0;
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}