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

package frc.robot.commands.SmartCommands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.Constants;
import frc.robot.SetPoints;
//import frc.robot.commands.VoidBanisher;
import frc.robot.commands.ArmCommands.ArmGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.WristCommands.WristGoToPosition;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.intake;
import frc.robot.subsystems.leds;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.arm;
import frc.robot.commands.IntakeCommands.*;
import frc.robot.commands.LEDCommands.Green;
import frc.robot.commands.LEDCommands.SpiritColors;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */

public class CollectFloorPose extends SequentialCommandGroup {
   // public intake intake;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public CollectFloorPose(elevator elevator,intake intake, wrist wrist,arm arm, leds leds){

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    addCommands(
    new ElevatorGoToPosition(elevator,SetPoints.ElevatorHome),
    new WristGoToPosition(wrist, SetPoints.WristCollect),
    new ArmGoToPosition(arm, SetPoints.armPickupFront),
    new IntakeMotorGoForCollecting(intake, 0.7).withTimeout(0.3),
    new IntakeGamePieceWithThreshold(intake,.7),
    //new HomePose(elevator, intake, wrist, arm),
    new Green(leds),
    new IntakeMotorGo(intake, 0.05)


    );
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
