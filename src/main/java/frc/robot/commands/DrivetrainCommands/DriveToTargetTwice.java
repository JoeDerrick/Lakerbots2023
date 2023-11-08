package frc.robot.commands.DrivetrainCommands;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

public class DriveToTarget extends CommandBase {

    drivetrain m_Drivetrain;
    Pose2d currentPose;
    Pose2d desiredPose;
    Rotation2d currentRot;
    Rotation2d desiredRot;
    Translation2d calculatedTranslation;
    double calculatedRotation;
    double minSpeed = -1.5;
    double maxSpeed = 1.5;
    double[] speeds;
 

    public DriveToTarget(drivetrain m_Drivetrain, Pose2d currentPose, Pose2d desiredPose, Rotation2d desiredRot) {

        this.m_Drivetrain = m_Drivetrain;
        this.currentPose = currentPose;
        this.desiredPose = desiredPose;
        this.desiredRot = desiredRot;
        addRequirements(m_Drivetrain);

    }

    @Override
    public void initialize() {
    m_Drivetrain.setTolerence(.01);
    //set setpoint to desired pose
    m_Drivetrain.setSetpoint(desiredPose.getX(), desiredPose.getY(), desiredRot.getDegrees());

    System.out.println("Desired x"+desiredPose.getX());
    System.out.println("Desireed y"+desiredPose.getY());
    System.out.println("Desired Theta"+desiredRot.getDegrees());

    


      

      
    }

    @Override
    public void execute() {
      //generates a current pose
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

      System.out.println("exe drive target X-"+calculatedTranslation.getX()+" Y-"+calculatedTranslation.getY()+"Th-"+calculatedRotation);
 
      //drive at translation and rotation
      m_Drivetrain.drive(calculatedTranslation, calculatedRotation, true, true);
    }

    @Override
    public void end(boolean interrupted) {
      //stop drivinb
     m_Drivetrain.drive(new Translation2d(0,0), 0, true, true);
    }

    @Override
    public boolean isFinished() {
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
    
    @Override
    public boolean runsWhenDisabled() {  
      return false;
    }
}