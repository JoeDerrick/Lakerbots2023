// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.WristCommands;

import frc.robot.subsystems.wrist;
import frc.robot.SetPoints;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class WristGoToPosition extends CommandBase {

  


  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final wrist m_WristSubsystem;
  public double position;
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public WristGoToPosition(wrist subsystem, double position) {
    m_WristSubsystem = subsystem;
    this.position = position; 
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_WristSubsystem.wristGoToPosition(position);
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
