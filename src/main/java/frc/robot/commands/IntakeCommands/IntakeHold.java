
package frc.robot.commands.IntakeCommands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.intake;


public class IntakeHold extends CommandBase {

   private final intake m_intake;
 



    public IntakeHold(intake subsystem) {



        m_intake = subsystem;
        addRequirements(m_intake);

    }

    @Override
    public void initialize() {
        m_intake.intakeHold();
    }

    @Override
    public void execute() {
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;

    }
}
