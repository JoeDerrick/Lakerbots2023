// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WristCommands;

import frc.robot.subsystems.wrist;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class WristHold extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final wrist m_WristSubsystem;
  
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WristHold(wrist subsystem) {
    m_WristSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_WristSubsystem.wristHold();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_WristSubsystem.wristGetPosition();
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_WristSubsystem.wristAtTargetPosition();
  }
}
