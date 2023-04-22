package frc.robot.commands.SmartCommands.Scores;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.SetPoints;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPositionAndHold;
import frc.robot.commands.ElevatorCommands.IsElevatorSafe;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.elevator;
import frc.robot.commands.SmartCommands.WristArmParallel;



public class ScoreHighCubePoseParallelFAST extends SequentialCommandGroup {
   
    public ScoreHighCubePoseParallelFAST(intake intake, wrist wrist,arm arm,elevator elevator){

        addCommands(
            
            new WristArmParallel(wrist, arm, SetPoints.WristScoreFront, SetPoints.armPlaceConeHighFront),
            new IsElevatorSafe(arm),
            new ElevatorGoToPosition(elevator, SetPoints.ElevatorHighCube)
        );
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
