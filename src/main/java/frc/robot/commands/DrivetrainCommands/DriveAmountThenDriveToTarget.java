package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;


public class DriveAmountThenDriveToTarget extends CommandBase {

    double speed;
    double amount;
    drivetrain m_Drivetrain;
    Pose2d currentPose;
    Pose2d desiredPose;
    Rotation2d desiredRot;
    Rotation2d currentRot;
    double[] speeds;
    Translation2d calculatedTranslation;
    double calculatedRotation;
    boolean enableIsFinished;


    public DriveAmountThenDriveToTarget(drivetrain m_Drivetrain, double speed, double amount, Pose2d desiredPose, Rotation2d desiredRot){
        this.m_Drivetrain = m_Drivetrain;
        this.speed = speed;
        this.amount = amount;
        this.desiredPose = desiredPose;
        this.desiredRot = desiredRot;
        addRequirements(m_Drivetrain);
    }

    @Override
    public void initialize(){
        m_Drivetrain.resetEncoders();

        //enableIsFinished is a boolean determining what section of the command should execute, ie; the drive amount
        // or Drive to target. this variable is also used to enable the ability to finish the command
        // enableIsFinished = false, drive amount executes and disables finishing
        // enableIsFinished = true, drive to target executs and enables finishing
        enableIsFinished = false;

        m_Drivetrain.zeroGyro();
        m_Drivetrain.setSetpoint(desiredPose.getX(), desiredPose.getY(), 0);

    }

    @Override
    public void execute(){
        //drive until amount has been reached
        //System.out.println("Average Encoder Value = "+m_Drivetrain.getAverageEncoderValue());

        if(m_Drivetrain.getAverageEncoderValue() < amount*80 && !enableIsFinished){

            System.out.println("Drive Amount Executing, Average Encoder Value = "+m_Drivetrain.getAverageEncoderValue());
            
            m_Drivetrain.drive(
                new Translation2d(speed, 0).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
                
            currentPose = m_Drivetrain.getPose();
            }
        //then start the drive to target procedure
        else{
            enableIsFinished = true;
            currentPose = m_Drivetrain.getPose();
            currentRot = m_Drivetrain.getRotation();
            //calculates pid speeds
            speeds = m_Drivetrain.getSpeeds(currentPose.getX(), currentPose.getY(), currentRot.getDegrees());

            //translation object with x and y speed
            calculatedTranslation = new Translation2d(
                speeds[0],
                speeds[1]
            );

            //roation with theta
            calculatedRotation = speeds[2];

            System.out.println("Executing Drive to Target, X - "+calculatedTranslation.getX()+" Y - "+calculatedTranslation.getY()+" Th - "+calculatedRotation);
        
            //drive at translation and rotation
            m_Drivetrain.drive(calculatedTranslation, 0, true, true);
        }
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        //stop driving
        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {

        //if the drive amount has finished, enable the ability to finish the command
        if(enableIsFinished){
            //if the x y and th are within a threshhold of 0.1, 0.1, and 3 respectivly, finish the commanf
            if(Math.abs(desiredPose.getX()-currentPose.getX()) < 0.1 &&
                Math.abs(desiredPose.getY()-currentPose.getY()) < 0.1 &&
                Math.abs(desiredRot.getDegrees()-currentRot.getDegrees()) < 3)
            {
            System.out.println("DRIVE TARGET FINISHED");
                return true;
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }
}
