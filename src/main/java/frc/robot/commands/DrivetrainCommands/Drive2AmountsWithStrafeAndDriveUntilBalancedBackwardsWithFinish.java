package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.wrist;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;


public class Drive2AmountsWithStrafeAndDriveUntilBalancedBackwardsWithFinish extends CommandBase {

    double initialXspeed;
    double initialYspeed;
    double secondXSpeed;
    double intermediateDistance;
    double amount;
    drivetrain m_Drivetrain;
    arm m_arm;
    wrist m_wrist;
    double pitch;
    double threshhold = 5;
    double p;
    boolean isDone;
    double armPos;
    double wristPos;

    public Drive2AmountsWithStrafeAndDriveUntilBalancedBackwardsWithFinish(arm m_arm, wrist m_wrist, double armPos, double wristPos, drivetrain m_Drivetrain, double initialXspeed, double initialYspeed, double secondXspeed, double amount, double intermediateDistance){
        this.m_Drivetrain = m_Drivetrain;
        this.m_arm = m_arm;
        this.m_wrist = m_wrist;
        this.initialXspeed = initialXspeed;
        this.initialYspeed = initialYspeed;
        this.secondXSpeed = secondXspeed;
        this.pitch = pitch;
        this.amount = amount;
        this.armPos = armPos;
        this.wristPos = wristPos;
        addRequirements(m_Drivetrain);
        p = 0.035; //0.04 
        isDone = false;
    }

    @Override
    public void initialize(){
        m_Drivetrain.resetEncoders();
        System.out.println("DRIVE AMOUNT INIT");
    }

    @Override
    public void execute(){
        pitch = -1*(m_Drivetrain.getPitch());//multiplied by -1 for going on charge the opposite way
        System.out.println(m_Drivetrain.getPitch());
        m_arm.armGoToPosition(armPos);
        m_wrist.wristGoToPosition(wristPos);

        if(!isAtDist1()){
        m_Drivetrain.drive(
                new Translation2d(initialXspeed, initialYspeed).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
                System.out.println("Not In Pitch Mode");
        }
        else if(!isDocked()){
            m_Drivetrain.drive(
                new Translation2d(secondXSpeed, 0).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
        }
        else if(!isEngaged()){
        m_Drivetrain.drive(
                new Translation2d(initialXspeed * p * pitch,  0).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
                System.out.println("In Pitch Mode");
        }
        else{
            m_Drivetrain.drive(
                new Translation2d(0,  0).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
            isDone = true;
        }
    }
    


    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {

        return isDone;
    }
    
    public boolean isDocked() {
     if (m_Drivetrain.getAverageEncoderValue() > amount){
        System.out.println("DRIVE AMOUNT DONE");
        return true;
      }
    else{
        return false;
        }

    }
    public boolean isEngaged(){
        if(m_Drivetrain.getAverageEncoderValue() > amount){
            if(pitch > -3 || pitch < 3){
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
    public boolean isAtDist1() {
        if (m_Drivetrain.getAverageEncoderValue() > intermediateDistance){
           System.out.println("DRIVE 1 AMOUNT DONE");
           return true;
         }
       else{
           return false;
           }
       }
}
