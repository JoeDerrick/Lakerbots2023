
package frc.robot.commands.DrivetrainCommands;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.CommandBase;


public class ChangeIfTurbo extends CommandBase {    
    
    public ChangeIfTurbo() {
        if(Constants.isTurbo == true){
            Constants.isTurbo = false;
        }
        else{
            Constants.isTurbo = true;
        }
    }

    @Override
    public void execute() {
    
    }
}