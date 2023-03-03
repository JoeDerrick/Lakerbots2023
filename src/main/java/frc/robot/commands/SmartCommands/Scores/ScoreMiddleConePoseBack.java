// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: SequentialCommandGroup.

package frc.robot.commands.SmartCommands.Scores;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.SetPoints;
import frc.robot.commands.VoidBanisher;
import frc.robot.commands.ArmCommands.ArmGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.WristCommands.WristGoToPosition;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.intake;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.elevator;
import frc.robot.commands.IntakeCommands.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */

public class ScoreMiddleConePoseBack extends SequentialCommandGroup {
   // public intake intake;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public ScoreMiddleConePoseBack(intake intake, wrist wrist,arm arm,elevator elevator){

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    addCommands(
    new ArmGoToPosition(arm, SetPoints.armPlaceConeMiddleBack),
    new WristGoToPosition(wrist, SetPoints.WristScoreFront),
    new ElevatorGoToPosition(elevator, SetPoints.ElevatorMiddle)

    );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
