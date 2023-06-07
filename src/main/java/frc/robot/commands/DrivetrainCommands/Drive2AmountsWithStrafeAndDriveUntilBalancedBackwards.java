package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;


public class Drive2AmountsWithStrafeAndDriveUntilBalancedBackwards extends CommandBase {

    double initialXspeed;
    double initialYspeed;
    double secondXSpeed;
    double intermediateDistance;
    double amount;
    drivetrain m_Drivetrain;
    double pitch;
    double threshhold = 5;
    double p;
    boolean isdone;

    public Drive2AmountsWithStrafeAndDriveUntilBalancedBackwards(drivetrain m_Drivetrain, double initialXspeed, double initialYspeed, double secondXspeed, double amount, double intermediateDistance){
        this.m_Drivetrain = m_Drivetrain;
        this.initialXspeed = initialXspeed;
        this.initialYspeed = initialYspeed;
        this.secondXSpeed = secondXspeed;
        this.pitch = pitch;
        this.amount = amount;
        addRequirements(m_Drivetrain);
        p = 0.035; //0.04 
        isdone = false;
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
        else{
            if(Math.abs(pitch) < 3){
                isdone = true;
            }
            m_Drivetrain.drive(
                    new Translation2d(initialXspeed * p * pitch,  0).times(Constants.Swerve.maxSpeed),
                    0 * Constants.Swerve.maxAngularVelocity,
                    false,
                    true);
                    System.out.println("In Pitch Mode");
            }
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {

        return isdone;
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
