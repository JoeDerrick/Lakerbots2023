// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WristCommands;

import frc.robot.subsystems.wrist;
import frc.robot.SetPoints;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class WristMoveOne extends CommandBase {

  


  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final wrist m_WristSubsystem;
  public XboxController pov;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WristMoveOne(wrist subsystem, XboxController pov) {
    m_WristSubsystem = subsystem;
    this.pov = pov;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(pov.getPOV() == 0){
    m_WristSubsystem.wristGoToPosition(m_WristSubsystem.wristGetPosition()+1);
    }
    else if(pov.getPOV() == 180){
      m_WristSubsystem.wristGoToPosition(m_WristSubsystem.wristGetPosition()-1);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_WristSubsystem.wristGetPosition();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    System.out.println("END WIRST STUFF");
    m_WristSubsystem.wristStop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    
    if(m_WristSubsystem.setPoint < 0){
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      System.out.println("end setpoint");
      
      return true;
    }
    else{
    return m_WristSubsystem.wristAtTargetPosition();
    }
  }
}
