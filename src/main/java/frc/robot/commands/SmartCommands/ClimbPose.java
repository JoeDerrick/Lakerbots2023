// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.Constants;
import frc.robot.SetPoints;
//import frc.robot.commands.VoidBanisher;
import frc.robot.commands.ArmCommands.ArmGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.WristCommands.WristGoToPosition;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.arm;
import frc.robot.commands.IntakeCommands.*;

public class ClimbPose extends SequentialCommandGroup {
   // public intake intake;

    public ClimbPose(elevator elevator,intake intake, wrist wrist,arm arm){

  
    addCommands(
    new ElevatorGoToPosition(elevator,SetPoints.ElevatorHome),
    new WristGoToPosition(wrist, SetPoints.WristCharge),
    new ArmGoToPosition(arm, SetPoints.armPickupFront),
    new IntakeMotorGo(intake, 0)

    );
    }

    @Override
    public boolean runsWhenDisabled() {
       
        return false;

  
    }
}
