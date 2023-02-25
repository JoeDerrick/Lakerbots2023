package frc.robot.commands.DrivetrainCommands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;


public class DriveAmount extends CommandBase {

    double amount;
    drivetrain m_Drivetrain;
    boolean IfForward;

    public DriveAmount(drivetrain m_Drivetrain, double amount, boolean IfForward){
        this.amount = amount;
        this.m_Drivetrain = m_Drivetrain;
        this.IfForward = IfForward;
        addRequirements(m_Drivetrain);
    }

    @Override
    public void execute(){
        if(IfForward){
            m_Drivetrain.drive(new Translation2d(0.1, 0), 0, false, false);
        }
        else{
            m_Drivetrain.drive(new Translation2d(0, 0.1), 0, false, false);
        }
    }
    @Override
    public void end(boolean interrupted) {
        System.out.println("ended");
        m_Drivetrain.drive(new Translation2d(0, 0), 0, false, false);
    }

    @Override
    public boolean isFinished() {
     if (m_Drivetrain.getAverageEncoderValue() < amount){
        return true;
      }
    else{
        return false;


        }
    }

}
