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

package frc.robot.commands.ElevatorCommands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.elevator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS

/**
 *
 */
public class ElevatorGoToPosition extends CommandBase {
    private final elevator m_ElevatorSubsystem;
    public double position;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public ElevatorGoToPosition(elevator subsystem, double position){
        m_ElevatorSubsystem = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);

    this.position = position;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    

    
    }
    @Override
    public void initialize(){
        System.out.println("ELEVATOR INIT!");
        m_ElevatorSubsystem.elevatorGoToPosition(position);
    }

    @Override
    public void execute(){
        System.out.println("ele exe");
        m_ElevatorSubsystem.elevatorGetPosition();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        if(m_ElevatorSubsystem.elevatorAtTargetPosition()){
            System.out.println("el fin");
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }
}
