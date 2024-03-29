
// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands.SmartCommands.Scores;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.SetPoints;
import frc.robot.commands.VoidBanisher;
import frc.robot.commands.ArmCommands.ArmGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.WristCommands.WristGoToPosition;

import frc.robot.subsystems.intake;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.elevator;
import frc.robot.commands.IntakeCommands.*;
import frc.robot.commands.SmartCommands.WristArmParallel;

public class ArmDownToScoreConeHigh extends SequentialCommandGroup {
   // public intake intake;

    public ArmDownToScoreConeHigh(intake intake, wrist wrist,arm arm,elevator elevator){

    addCommands(
    new ArmGoToPosition(arm, SetPoints.ArmDownToScoreConeHigh)
    );
    }


    @Override
    public boolean runsWhenDisabled() {
      
        return false;
    }
}
