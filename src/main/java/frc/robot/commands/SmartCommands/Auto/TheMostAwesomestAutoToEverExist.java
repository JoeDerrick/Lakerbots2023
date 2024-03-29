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
import frc.robot.SetPoints;
import frc.robot.commands.DrivetrainCommands.DriveAmount;
import frc.robot.commands.DrivetrainCommands.DriveAmountAndDriveUntilBalanced;
import frc.robot.commands.DrivetrainCommands.DriveAmountAndDriveUntilBalancedBackwards;
import frc.robot.commands.DrivetrainCommands.DriveAmountAtDifferentSpeedsAfterDistanceMovingArmAndWrist;
import frc.robot.commands.DrivetrainCommands.DriveAmountWhileCollecting;
import frc.robot.commands.DrivetrainCommands.DriveUntilBalanced;
import frc.robot.commands.DrivetrainCommands.StrafeAmount;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.IntakeCommands.IntakeCube;
import frc.robot.commands.IntakeCommands.IntakeMotorGo;
import frc.robot.commands.LEDCommands.SpiritColors;
import frc.robot.commands.SmartCommands.ClimbPoseBack;
import frc.robot.commands.SmartCommands.ClimbPoseBackParallel;
import frc.robot.commands.SmartCommands.CollectFloorPoseBack;
import frc.robot.commands.SmartCommands.DriveAmountUntilBalancedAndArmClimbParallel;
import frc.robot.commands.SmartCommands.HomePose;
import frc.robot.commands.SmartCommands.Scores.ScoreHighConePose;
import frc.robot.commands.SmartCommands.Scores.ScoreHighCubePose;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.SmartCommands.Balance;
import frc.robot.commands.SmartCommands.ClimbPose;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.leds;
import frc.robot.commands.SmartCommands.ClimbPose;
    
/**
 *
 */
public class TheMostAwesomestAutoToEverExist extends SequentialCommandGroup {

  

    public TheMostAwesomestAutoToEverExist(intake intake, wrist wrist, arm arm, elevator elevator, drivetrain drivetrain, leds leds){

    addCommands(
        
        new ScoreHighCubePose(intake, wrist, arm, elevator),//score pose 
        new IntakeMotorGo(intake, -0.2).withTimeout(.1),//score | |                          |  |
        new ElevatorGoToPosition(elevator, SetPoints.ElevatorHome),  // \/\/ over the charge station \/ \/
        new DriveAmountAtDifferentSpeedsAfterDistanceMovingArmAndWrist(drivetrain, arm, wrist, 170, 20, -0.12, -0.37, 0,0, SetPoints.armChargeBack, SetPoints.WristChargeBack),
        new DriveAmountWhileCollecting(intake, drivetrain, 30, -0.02, 0.02, 0.7, true), //get the cube from taxi pos
        new DriveAmountWhileCollecting(intake, drivetrain, 30, 0.02, -0.02, 0, true), // go back to taxi pos
        new edu.wpi.first.wpilibj2.command.WaitCommand(0.5),
        new DriveAmountAndDriveUntilBalancedBackwards(drivetrain, 0.25, 54),//engage
        new ScoreHighConePose(intake, wrist, arm, elevator),//score pose
        new IntakeMotorGo(intake, 1).withTimeout(1), //score with power
        new IntakeMotorGo(intake, 0),//stop intake cause 100 power bad for motor
        new SpiritColors(leds) // Be cool and shwaggy

        
       // new Balance(drivetrain)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;

    }
}
