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

import frc.robot.commands.DrivetrainCommands.DriveAmount;
import frc.robot.commands.DrivetrainCommands.DriveAmountWhileCollecting;
import frc.robot.commands.IntakeCommands.IntakeCube;
import frc.robot.commands.IntakeCommands.IntakeMotorGo;
import frc.robot.commands.SmartCommands.ClimbPoseBack;
import frc.robot.commands.SmartCommands.CollectFloorPoseBack;
import frc.robot.commands.SmartCommands.HomePose;
import frc.robot.commands.SmartCommands.Scores.ScoreHighConePose;
import frc.robot.commands.SmartCommands.Scores.ScoreHighCubePose;
import frc.robot.subsystems.elevator;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.leds;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.drivetrain;
import frc.robot.commands.SmartCommands.Balance;

import frc.robot.subsystems.wrist;


/**
 *
 */
public class PickupCubeRedLoadingStation extends SequentialCommandGroup {

   

    public PickupCubeRedLoadingStation(intake intake, wrist wrist, arm arm, elevator elevator, drivetrain drivetrain, leds leds){
 
  
    addCommands(
        
        new ScoreHighConePose(intake, wrist, arm, elevator),
        new IntakeMotorGo(intake, -0.2).withTimeout(.3),
        //new HomePose(elevator, intake, wrist, arm),
        new CollectFloorPoseBack(elevator, intake, wrist, arm, leds).withTimeout(6),//added because it wasnt driving
        new DriveAmountWhileCollecting(intake, drivetrain,163,-.4,-0,0.2, true)
        
       // new Balance(drivetrain)
        );
    }



    @Override
    public boolean runsWhenDisabled() {
  
        return false;

    
    }
}
