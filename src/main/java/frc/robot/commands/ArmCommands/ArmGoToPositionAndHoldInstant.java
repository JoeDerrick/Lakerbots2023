// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ArmCommands;

import frc.robot.subsystems.arm;
import frc.robot.subsystems.intake;
import edu.wpi.first.wpilibj2.command.CommandBase;


/** An example command that uses an example subsystem. */
public class ArmGoToPositionAndHoldInstant extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final arm m_ArmSubsystem;
  public double position;
  private final intake m_intake;
  
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ArmGoToPositionAndHoldInstant(intake intake,arm arm,double position) {
    m_ArmSubsystem = arm;
    m_intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(arm);

    addRequirements(m_intake);

    this.position = position;
    
    
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_ArmSubsystem.armGoToPosition(position);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_ArmSubsystem.armGetPosition();
    m_intake.intakeMotorGo(0.1);

  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
