package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;


public class DriveUntilBalanced extends CommandBase {


    double speed;
    drivetrain m_Drivetrain;

    double pitch;


    double threshhold = 5;
    double p;

    public DriveUntilBalanced(drivetrain m_Drivetrain, double speed){
        
        this.m_Drivetrain = m_Drivetrain;
        this.speed = speed;
        this.pitch = pitch;
        addRequirements(m_Drivetrain);
        p = 0.04;
    }

    @Override
    public void initialize(){
        m_Drivetrain.resetEncoders();
        System.out.println("DriveUntilBaalancedINIT");
    }

    @Override
    public void execute(){

        pitch = m_Drivetrain.getPitch();
        
        System.out.println(m_Drivetrain.getPitch());
        /* 
        if(IfForward){
            m_Drivetrain.drive(new Translation2d(0.25, 0), 0, false, true);
        }
        else{
            m_Drivetrain.drive(new Translation2d(0, 0.25), 0, false, true);
        }
        */
        m_Drivetrain.drive(
                new Translation2d(speed * p * pitch, 0).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("DriveUntilBaalancedEND");

        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {
     /* 
        if (Math.abs(pitch) < threshhold){
        System.out.println("DRIVE balalalal DONE");
        return true;
      }
    else{
        return false;


        }
        */
        return false;
    }



}
