package frc.robot.subsystems;

import frc.robot.SwerveModule;
import frc.robot.commands.DrivetrainCommands.Drive;
import frc.robot.Constants;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.List;
import java.util.function.ToDoubleFunction;

import com.ctre.phoenix.sensors.Pigeon2;

public class drivetrain extends SubsystemBase {
    // public Deadband deadband = new Deadband();
    public SwerveDriveOdometry swerveOdometry;
    public drivetrain m_drivetrain;
    public SwerveModule[] mSwerveMods;
    public Pigeon2 gyro;
    
    //odometry
    private PIDController m_XPid;
    private PIDController m_YPid;
    private PIDController m_ThetaPid;
    public double setPoint;
    public double[] Speeds = {0,0};

    private double m_driveToTargetTolerance = Constants.Swerve.DriveToTargetTolerance;

    public drivetrain() {
        gyro = new Pigeon2(Constants.Swerve.pigeonID);
        gyro.configFactoryDefault();
        zeroGyro();
        m_XPid = new PIDController(0.6, 0, 0);
        m_YPid = new PIDController(0.6, 0, 0);
        m_ThetaPid = new PIDController(0.01, 0, 0);

        mSwerveMods = new SwerveModule[] {
                new SwerveModule(0, Constants.Swerve.Mod0.constants),
                new SwerveModule(1, Constants.Swerve.Mod1.constants),
                new SwerveModule(2, Constants.Swerve.Mod2.constants),
                new SwerveModule(3, Constants.Swerve.Mod3.constants)
        };
        swerveOdometry = new SwerveDriveOdometry(Constants.Swerve.swerveKinematics, getYaw(), getModulePositions());
        Timer.delay(1.0);
        for(int i = 0; i<10; i++){
            resetModulesToAbsolute();
        }
    }

    

    public void resetEncoders() {
        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
        for(int i = 0; i<10;i++){
            module0.resetDriveEncoder();
            module1.resetDriveEncoder();
            module2.resetDriveEncoder();
            module3.resetDriveEncoder();
        }
    }
    public double getAverageEncoderValue() {
        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
        return (((Math.abs(module0.getWheelPosition()) +
            Math.abs(module1.getWheelPosition()) +
            Math.abs(module2.getWheelPosition()) +
            Math.abs(module3.getWheelPosition())// Do the math to make this into inches
        ) / 4) / (1350)); //TODO Do this math
        // 1280 ticks per revolution (2048 ticks per rev x 6.8 Gear Ratio / 2pi x3.5
                      // inches per rev)
    }

    public double deadband(double val) {

        if (Math.abs(val) > 0.03) {
            return (val);
        } else {
            return (0);
        }
    }

    /*
     * By pausing init for a second before setting module offsets, we avoid a bug
     * with inverting motors.
     * See https://github.com/Team364/BaseFalconSwerve
     * 
     * rve/issues/8 for more info.
     */

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop) {

        SwerveModuleState[] swerveModuleStates = Constants.Swerve.swerveKinematics.toSwerveModuleStates(
                fieldRelative ? ChassisSpeeds.fromFieldRelativeSpeeds(
                        deadband(translation.getX()),
                        deadband(translation.getY()),
                        rotation,
                        getYaw())
                        : new ChassisSpeeds(
                                deadband(translation.getX()),
                                deadband(translation.getY()),
                                rotation));
        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.maxSpeed);

        for (SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
        }
    }

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.maxSpeed);

        for (SwerveModule mod : mSwerveMods) {
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }



///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///----------------------------- Following section is for odometry------------------------------------
/// most of this code is from 131 CHAOS (thanks btw)
//added 9/13/23



    //checker if its in tolerance of the target
    public boolean CHAOSatTarget() {
        boolean isXTolerable = Math.abs(getPose().getX() - m_XPid.getSetpoint()) <= m_driveToTargetTolerance;
        boolean isYTolerable = Math.abs(getPose().getY() - m_YPid.getSetpoint()) <= m_driveToTargetTolerance;
        return isXTolerable && isYTolerable && m_ThetaPid.atSetpoint();
    
      }
    //target setter
    public void CHAOSsetTarget(double x, double y, Rotation2d angle) {
        m_XPid.setSetpoint(x);
        m_YPid.setSetpoint(y);
        m_ThetaPid.setSetpoint(angle.getDegrees());
      }

    //move to target
    public void CHAOSmoveToTarget(double maxTranslationSpeedPercent) {
        Pose2d pose = getPose();

        //mathUtil.clamp has the purpose of making sure the speeds are within a specific range,
        // if x is below the min the min will be used for x
        //if x is above the max the max will be used for x
        //if x is within the range just use x
        double x = MathUtil.clamp(m_XPid.calculate(pose.getX()), -maxTranslationSpeedPercent, maxTranslationSpeedPercent);
        double y = MathUtil.clamp(m_YPid.calculate(pose.getY()), -maxTranslationSpeedPercent, maxTranslationSpeedPercent);
        double angle = m_ThetaPid.calculate(pose.getRotation().getDegrees());
        CHAOSmoveFieldRelativeForPID(x, y, angle);
      }

      //fancy drive
    public void CHAOSmoveFieldRelativeForPID(double xMetersPerSecond, double yMetersPerSecond, double omegaRadianPerSecond){
        drive(new Translation2d(xMetersPerSecond, yMetersPerSecond), omegaRadianPerSecond, true, true);
      }





    //pose getter
    //pose is a x y and theta position
    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    //zero it
    public void CHAOSresetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    }
    
        //TODO: add this
    //removed until we figure out if the default module config will work
    /* 
    public void driveToPositionInit() {

        SwerveModule module0 = mSwerveMods[0];
        SwerveModule module1 = mSwerveMods[1];
        SwerveModule module2 = mSwerveMods[2];
        SwerveModule module3 = mSwerveMods[3];
        module0.driveToPositionInit();
        module1.driveToPositionInit();
        module2.driveToPositionInit();
        module3.driveToPositionInit();
      }
      */

      public void CHAOSresetPids() {
        m_XPid.reset();
        m_YPid.reset();
        m_ThetaPid.reset();
        CHAOSsetDriveTranslationTolerance(Constants.Swerve.DriveToTargetTolerance);
      }
      public void CHAOSsetDriveTranslationTolerance(double tolerance) {
        m_driveToTargetTolerance = tolerance;
      }


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//new pid code by me

public void setSetpoint(double x, double y){
    m_XPid.setSetpoint(x);
    m_YPid.setSetpoint(y);
}

public double[] getSpeeds(double currentX, double currentY){
    Speeds[0] = m_XPid.calculate(currentX);
    Speeds[1] = m_YPid.calculate(currentY);
    return(Speeds);
}

public void setTolerence(double t){
    m_XPid.setTolerance(t);
    m_YPid.setTolerance(t);
}

public boolean isAtDesired(){
    if(m_XPid.atSetpoint() && m_YPid.atSetpoint()){
        return true;
    }
    else{
        return false;
    }
}







    public SwerveModuleState[] getModuleStates() {
        SwerveModuleState[] states = new SwerveModuleState[4];
        for (SwerveModule mod : mSwerveMods) {
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions() {
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for (SwerveModule mod : mSwerveMods) {
            positions[mod.moduleNumber] = mod.getPosition();
        }
        return positions;
    }

    public void zeroGyro() {
        gyro.setYaw(0);
    }

    public void reverseGryo(){
        gyro.setYaw(180);
    }
    public Rotation2d getYaw() {
        return (Constants.Swerve.invertGyro) ? Rotation2d.fromDegrees(360 - gyro.getYaw())
                : Rotation2d.fromDegrees(gyro.getYaw());
    }

    public double getPitch() {
        return gyro.getRoll();//pigeon orientation
    }

    public void resetModulesToAbsolute() {
        for (SwerveModule mod : mSwerveMods) {
            mod.resetToAbsolute();
        }
    }

    @Override
    public void periodic() {

    SmartDashboard.putNumber("odometry x", getPose().getX());
    SmartDashboard.putNumber("odometry y", getPose().getY());
    SmartDashboard.putNumber("odometry th", getPose().getRotation().getDegrees());
    
        swerveOdometry.update(getYaw(), getModulePositions());


        //for swerve tuning

        for (SwerveModule mod : mSwerveMods) {
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Cancoder", mod.getCanCoder().getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
            SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);
        }
        //System.out.println(getPose());
        //---- 0 = Back Right
        //---- 1 = Front right
        //---- 2 = Back Left
        //---- 3 = Front Left
    }
}