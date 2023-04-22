package frc.robot.commands.SmartCommands.Scores;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.SetPoints;
import frc.robot.commands.ArmCommands.ArmGoToPositionAndHold;
import frc.robot.commands.ElevatorCommands.ElevatorGoToPositionAndHold;
import frc.robot.commands.SmartCommands.WristArmParallel;
import frc.robot.commands.WristCommands.WristGoToPositionAndHold;
import frc.robot.subsystems.intake;
import frc.robot.subsystems.wrist;
import frc.robot.subsystems.arm;
import frc.robot.subsystems.elevator;

public class ScoreMiddleCubePoseParallel extends SequentialCommandGroup {
   
    public ScoreMiddleCubePoseParallel(intake intake, wrist wrist,arm arm,elevator elevator){

        addCommands(
            new WristArmParallel(wrist, arm, SetPoints.WristScoreFrontCube, SetPoints.armPlaceConeHighFront),
            new ElevatorGoToPositionAndHold(intake, elevator, SetPoints.ElevatorMiddleCube)

        );
    }

    @Override
    public boolean runsWhenDisabled() {
    
        return false;

    }
}
