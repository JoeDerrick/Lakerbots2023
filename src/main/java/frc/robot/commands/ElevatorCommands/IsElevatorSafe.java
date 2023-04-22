package frc.robot.commands.ElevatorCommands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import java.util.function.DoubleSupplier;

import frc.robot.SetPoints;
import frc.robot.subsystems.arm;


public class IsElevatorSafe extends CommandBase {

    private final arm m_arm;

    public IsElevatorSafe(arm subsystem) {
        m_arm = subsystem;
        addRequirements(m_arm);

         
    }

    @Override
    public boolean isFinished() {
        return m_arm.armGetPosition() <= SetPoints.ElevatorSafePoint;
    }

}
