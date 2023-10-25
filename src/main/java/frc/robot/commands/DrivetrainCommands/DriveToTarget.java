

package frc.robot.commands.DrivetrainCommands;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;

import java.util.function.DoubleSupplier;


public class DriveToTarget extends CommandBase {

    drivetrain m_Drivetrain;
    Pose2d currentPose;
    Pose2d desiredPose;
    Translation2d calculatedTranslation;
    double calcuatedRotation;
    double speed;
    double minSpeed = -1.5;
    double maxSpeed = 1.5;
 

    public DriveToTarget(drivetrain m_Drivetrain, Pose2d currentPose, Pose2d desiredPose, double speed) {

        this.m_Drivetrain = m_Drivetrain;
        this.currentPose = currentPose;
        this.desiredPose = desiredPose;
        this.speed = speed;
        addRequirements(m_Drivetrain);

    }

    @Override
    public void initialize() {
    m_Drivetrain.setTolerence(.01);
    m_Drivetrain.setSetpoint(desiredPose.getX(), desiredPose.getY());
    System.out.println("Desired x"+desiredPose.getX());
    System.out.println("Desireed y"+desiredPose.getY());

    


      

      //calcuatedRotation = desiredPose.getRotation().getDegrees()-currentPose.getRotation().getDegrees();
      
    }

    @Override
    public void execute() {
      currentPose = m_Drivetrain.getPose();
      //System.out.println("executing driveTarget X:"+currentPose.getX() + "Y:" +currentPose.getY());
      
      //-- Calculate the Error for both x and y and display here---//

      //System.out.println("x:"+currentPose.getX());
      //System.out.println("y"+currentPose.getY());
      
      calculatedTranslation = new Translation2d(
        MathUtil.clamp(speed*m_Drivetrain.getSpeeds(currentPose.getX(),currentPose.getY())[0], minSpeed, maxSpeed), 
        MathUtil.clamp(speed*m_Drivetrain.getSpeeds(currentPose.getX(),currentPose.getY())[1], minSpeed, maxSpeed)
      );


      System.out.println("exe drive target X-"+calculatedTranslation.getX()+" Y-"+calculatedTranslation.getY());
      
      /*calculatedTranslation = new Translation2d(
        MathUtil.clamp(speed*m_Drivetrain.getSpeeds(currentPose.getX(),currentPose.getY())[0]*(desiredPose.getX()-currentPose.getX()), minSpeed, maxSpeed), 
        MathUtil.clamp(speed*m_Drivetrain.getSpeeds(currentPose.getX(),currentPose.getY())[1]*(desiredPose.getY()-currentPose.getY()), minSpeed, maxSpeed) */
        //-------------speed*?---- why speed times?
     
      


      m_Drivetrain.drive(calculatedTranslation, 0, true, true);



      //drive at set vector -1 to 1 (x,y)
      //if(desired x - starting x   is positive): vx = speed
      //else: vx = -speed
      //
      //if(desired y - starting y   is positive): vy = speed
      //else: vy = -speed
      //drive at vx ,vy
    }

    @Override
    public void end(boolean interrupted) {
     m_Drivetrain.drive(new Translation2d(0,0), 0, true, true);
    }

    @Override
    public boolean isFinished() {
      return m_Drivetrain.isAtDesired();
      /*//wrong
      if(Math.abs(m_Drivetrain.getPose().getX()-desiredPose.getX()) < 2 && Math.abs(m_Drivetrain.getPose().getY()-desiredPose.getY()) < 2 && Math.abs(m_Drivetrain.getPose().getRotation().getDegrees()-desiredPose.getRotation().getDegrees()) < 2){
        return true;
      }
      return false; 
      */
      //if current x - starting x within a threshold of desired x and
      //if current y - starting y within a threshold of desired y:
      //  return true
      //else return false
    }
    

    @Override
    public boolean runsWhenDisabled() {  
      return false;
    }
}
