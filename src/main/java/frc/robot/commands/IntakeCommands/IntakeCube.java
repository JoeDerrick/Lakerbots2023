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

package frc.robot.commands.IntakeCommands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import frc.robot.subsystems.intake;



    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS




public class IntakeCube extends CommandBase {

    public double startTime;
    public double currentTime;
    public intake intake;
    public boolean isFinished = false;
    public double speed;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

    public IntakeCube(intake intake, double speed){
        this.intake = intake;
        this.speed = speed;
        addRequirements(intake);
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    
    }

    @Override
    public void initialize(){
        startTime = System.currentTimeMillis();
        isFinished = false;
    }

    @Override
    public void execute(){
        
        intake.intakeMotorGo(speed);
            
            
       
    }

    @Override
    public boolean runsWhenDisabled() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
        return false;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DISABLED
    }

    @Override
    public boolean isFinished(){
        return isFinished;
    }
}
