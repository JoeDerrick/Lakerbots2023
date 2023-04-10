package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;


public class DriveAmountWithStrafeAndDriveUntilBalancedBackwards extends CommandBase {

    double xspeed;
    double yspeed;
    double amount;
    drivetrain m_Drivetrain;
    double pitch;
    double threshhold = 5;
    double p;

    public DriveAmountWithStrafeAndDriveUntilBalancedBackwards(drivetrain m_Drivetrain, double xspeed, double yspeed, double amount){
        this.m_Drivetrain = m_Drivetrain;
        this.xspeed = xspeed;
        this.yspeed = yspeed;
        this.pitch = pitch;
        this.amount = amount;
        addRequirements(m_Drivetrain);
        p = 0.04; //RIDE Match 1: 0.04 
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

        if(!isDocked()){
        m_Drivetrain.drive(
                new Translation2d(xspeed, yspeed).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
                System.out.println("Not In Pitch Mode");
        }
        else{
        m_Drivetrain.drive(
                new Translation2d(xspeed * p * pitch,  0).times(Constants.Swerve.maxSpeed),
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

        return false;
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
}
