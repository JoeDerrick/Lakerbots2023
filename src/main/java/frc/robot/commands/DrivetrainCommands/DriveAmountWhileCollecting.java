package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.subsystems.drivetrain;
import frc.robot.Constants;
import frc.robot.subsystems.intake;


public class DriveAmountWhileCollecting extends CommandBase {

    double amount;
    double speed;
    drivetrain m_Drivetrain;
    boolean IfForward;
    intake m_intake;

    public DriveAmountWhileCollecting(intake intake, drivetrain m_Drivetrain, double amount, double speed, boolean IfForward){
        this.amount = amount;
        this.m_Drivetrain = m_Drivetrain;
        this.IfForward = IfForward;
        this.speed = speed;
        m_intake = intake;
        addRequirements(m_Drivetrain);
        addRequirements(m_intake);
    }

    @Override
    public void initialize(){
        m_Drivetrain.resetEncoders();
        System.out.println("DRIVE AMOUNT INIT");
    }

    @Override
    public void execute(){
        m_intake.intakeMotorGo(0.2);
        
        System.out.println(m_Drivetrain.getAverageEncoderValue());
        /* 
        if(IfForward){
            m_Drivetrain.drive(new Translation2d(0.25, 0), 0, false, true);
        }
        else{
            m_Drivetrain.drive(new Translation2d(0, 0.25), 0, false, true);
        }
        */
        m_Drivetrain.drive(
                new Translation2d(speed, 0.035).times(Constants.Swerve.maxSpeed),
                0 * Constants.Swerve.maxAngularVelocity,
                false,
                true);
    }
    
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {
     if (m_Drivetrain.getAverageEncoderValue() > amount){
        System.out.println("DRIVE AMOUNT DONE");
        return true;
      }
    else{
        return false;


        }
    }

}
