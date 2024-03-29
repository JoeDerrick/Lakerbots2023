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

package frc.robot.commands.SmartCommands.Auto;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmCommands.ArmGoToPositionAndHoldInstant;
import frc.robot.commands.DrivetrainCommands.Drive2AmountsWithStrafeAndDriveUntilBalancedBackwards;
import frc.robot.commands.DrivetrainCommands.Drive2AmountsWithStrafeAndDriveUntilBalancedBackwardsWithFinish;
import frc.robot.commands.DrivetrainCommands.DriveAmountAndDriveUntilBalancedBackwards;
import frc.robot.commands.DrivetrainCommands.DriveAmountAtDifferentSpeedsAfterDistanceMovingArmAndWrist;
import frc.robot.commands.DrivetrainCommands.DriveUntilBalanced;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.IntakeCommands.IntakeMotorGo;

import frc.robot.commands.SmartCommands.Scores.ScoreHighCubePose;
import frc.robot.commands.SmartCommands.Scores.ScoreHighCubePoseFast;
import frc.robot.commands.SmartCommands.Scores.ScoreMiddleCubePose;
import frc.robot.commands.WristCommands.WristGoToPosition;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.SetPoints;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.leds;
    
/**
 *
 */
public class RedHighCubeAndTaxiAndPickupAndEngageCubeShoot extends SequentialCommandGroup {

  

    public RedHighCubeAndTaxiAndPickupAndEngageCubeShoot(intake intake, wrist wrist, arm arm, elevator elevator, drivetrain drivetrain, leds leds){

    addCommands(

    new ScoreHighCubePoseFast(intake, wrist, arm, elevator),
    new IntakeMotorGo(intake, -0.2).withTimeout(.25),
    new ElevatorGoToPosition(elevator, SetPoints.ElevatorHome),
    new IntakeMotorGo(intake, 0.5),
    new DriveAmountAtDifferentSpeedsAfterDistanceMovingArmAndWrist(drivetrain, arm, wrist, 
    180, 20,
     -0.12, -0.37,
    -0,-0.04,
    SetPoints.armChargeBack, SetPoints.WristChargeBack),
    new edu.wpi.first.wpilibj2.command.WaitCommand(0.1),
    new ArmGoToPositionAndHoldInstant(intake, arm, SetPoints.armPlaceConeHighFront),
    new Drive2AmountsWithStrafeAndDriveUntilBalancedBackwards(drivetrain, 0.35, 0.6, 0.3, 65, 55).withTimeout(5),
    new ScoreMiddleCubePose(intake, wrist, arm, elevator).withTimeout(0.6),
    new IntakeMotorGo(intake, -1).withTimeout(0.2),
    new IntakeMotorGo(intake, 0).withTimeout(0.01),
    //new DriveUntilBalanced(drivetrain, 0.25),
    new DriveAmountAndDriveUntilBalancedBackwards(drivetrain, 0.25, 0)
    
    );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;

    }
}
