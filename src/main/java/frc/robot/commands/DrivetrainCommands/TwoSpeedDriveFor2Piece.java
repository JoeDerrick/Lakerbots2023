package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.wrist;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;
import frc.robot.SetPoints;


public class TwoSpeedDriveFor2Piece extends CommandBase {

    double speed;
    double amount;
    double initialXSpeed;
    double secondXSpeed;
    double ySpeed;
    double secondYSpeed;
    double armSetpoint;
    double wristSetpoint;
    drivetrain m_Drivetrain;
    arm m_arm;
    wrist m_wrist;
    double pitch;
    double intermediateDistance;
    double p;
    double initialMagnitude;

    public TwoSpeedDriveFor2Piece(drivetrain m_Drivetrain, arm m_arm, wrist m_wrist, double amount, double intermediateDistance, double initialXSpeed, double secondXSpeed, double ySpeed,double secondYSpeed, double armSetpoint, double wristSetpoint){
        this.m_Drivetrain = m_Drivetrain;
        this.m_arm = m_arm;
        this.m_wrist = m_wrist;
        this.initialXSpeed = initialXSpeed;
        this.secondXSpeed = secondXSpeed;
        this.ySpeed = ySpeed;
        this.secondYSpeed = secondYSpeed;
        this.intermediateDistance = intermediateDistance;
        this.armSetpoint = armSetpoint;
        this.wristSetpoint = wristSetpoint;
        this.pitch = pitch;
        this.amount = amount;

       
        addRequirements(m_Drivetrain);
        addRequirements(m_wrist);
        addRequirements(m_arm);
        p = 0.05; //RIDE Match 1: 0.04 
    }

    @Override
    public void initialize(){
        m_Drivetrain.resetEncoders();
        System.out.println("DRIVE AMOUNT INIT");
    }

    @Override
    public void execute(){
        
        System.out.println("Drive Amount"+m_Drivetrain.getAverageEncoderValue());
        m_arm.armGoToPosition(armSetpoint);
        m_wrist.wristGoToPosition(wristSetpoint);
        
        if(!isAtFirst()){
            m_Drivetrain.drive(
                new Translation2d(initialXSpeed, ySpeed).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
        }
        else{
            m_Drivetrain.drive(
                new Translation2d(secondXSpeed,  secondYSpeed).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
        }
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {

        return isAtDistance();
    }
    
    public boolean isAtFirst() {
     if (m_Drivetrain.getAverageEncoderValue() > intermediateDistance){
        System.out.println("DRIVE AMOUNT DONE");
        return true;
      }
    else{
        return false;
        }
    }
    public boolean isAtDistance() {
        if (m_Drivetrain.getAverageEncoderValue() > amount){
           System.out.println("Total DRIVE AMOUNT DONE");
           return true;
         }
       else{
           return false;
           }
       }
}
