package frc.robot.commands.DrivetrainCommands;

import frc.robot.Constants;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.leds;

//import java.lang.ModuleLayer.Controller;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DriveWithSlowAndTurbo extends CommandBase {
    private drivetrain m_Drivetrain;
    private leds m_leds;
    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;
    private XboxController pov;
    private boolean ifTurbo;

    public DriveWithSlowAndTurbo(drivetrain m_Drivetrain, DoubleSupplier translationSup, DoubleSupplier strafeSup,
            DoubleSupplier rotationSup, BooleanSupplier robotCentricSup, XboxController pov, boolean ifTurbo, leds m_leds) {
        this.m_Drivetrain = m_Drivetrain;
        addRequirements(m_Drivetrain);

        this.translationSup = translationSup;
        this.strafeSup = strafeSup;
        this.rotationSup = rotationSup;
        this.robotCentricSup = robotCentricSup;
        this.pov = pov;
        this.ifTurbo = ifTurbo;
        this.m_leds = m_leds;
    }

    @Override
    public void execute() {
       // System.out.println("pitch =" +m_Drivetrain.getPitch());
        /* Get Values, Deadband */
        double translationVal = MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband);
        double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband);
        double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);

        /* Drive */
        m_Drivetrain.drive(
                new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed),
                rotationVal * Constants.Swerve.maxAngularVelocity,
                !robotCentricSup.getAsBoolean(),
                true);
        // switch case (fancy if statement) for moving slowly in all 4 dpad directions
        // defaults to driving normally if no dpad direction
        // ----TO DO - Abstract the 0.1 and assign as a variable that is easy to tweak
        // in Constants. figure out how to make it less than the deadband.
        switch (pov.getPOV()) {
            // up
            case 0:
                m_Drivetrain.drive(
                        new Translation2d(0.15, 0).times(Constants.Swerve.maxSpeed),
                        rotationVal * Constants.Swerve.maxAngularVelocity,
                        !robotCentricSup.getAsBoolean(),
                        true);
                break;
            // down
            case 180:
                m_Drivetrain.drive(
                        new Translation2d(-0.15, 0).times(Constants.Swerve.maxSpeed),
                        rotationVal * Constants.Swerve.maxAngularVelocity,
                        !robotCentricSup.getAsBoolean(),
                        true);
                break;
            // left
            case 270:
                m_Drivetrain.drive(
                        new Translation2d(0, 0.1).times(Constants.Swerve.maxSpeed),
                        rotationVal * Constants.Swerve.maxAngularVelocity,
                        !robotCentricSup.getAsBoolean(),
                        true);
                break;
            // right
            case 90:
                m_Drivetrain.drive(
                        new Translation2d(0, -0.1).times(Constants.Swerve.maxSpeed),
                        rotationVal * Constants.Swerve.maxAngularVelocity,
                        !robotCentricSup.getAsBoolean(),
                        true);
                break;
            // default
            default:
                if(ifTurbo){
                m_Drivetrain.drive(
                    new Translation2d(translationVal*1.25, strafeVal*1.25).times(Constants.Swerve.turboSpeed),
                    rotationVal * Constants.Swerve.maxAngularVelocity,
                    !robotCentricSup.getAsBoolean(),
                    true);
                m_leds.green();
                }
                else{
                m_Drivetrain.drive(
                        new Translation2d(translationVal, strafeVal).times(Constants.Swerve.maxSpeed),
                        rotationVal * Constants.Swerve.maxAngularVelocity,
                        !robotCentricSup.getAsBoolean(),
                        true);
                m_leds.spiritColors();
                }
                break;

        }

    }
}