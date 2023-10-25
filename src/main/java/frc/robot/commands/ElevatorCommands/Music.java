package frc.robot.commands.ElevatorCommands;

import frc.robot.Constants;
import frc.robot.subsystems.drivetrain;
import frc.robot.subsystems.elevator;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.music.Orchestra;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;
import com.ctre.phoenix.music.Orchestra;


public class Music extends CommandBase {    
    private elevator elevator;  
    private Orchestra music;
    private String path;
    private TalonFX elevatorMotor;
   




    

    public Music(elevator elevator, String path) {
        this.path = path;
        this.elevator = elevator;
        elevatorMotor= elevator.elevatorMotor;
        addRequirements(elevator);

        //addRequirements(music);
        music = new Orchestra();

       
    }

    @Override
    public void initialize(){
        /*elevator.musicAddTalon();
        elevator.musicload(path);
        elevator.musicplay();
        */
        System.out.println("musicInit");
        music.addInstrument(elevatorMotor);
        music.loadMusic(path);
        music.play();
        

    }

    @Override
    public void execute() {
        System.out.println("musicExecute");

        
    }


    @Override
    public boolean isFinished() {
        return elevator.isFinishedPlayingMusic();

    }

 //----   
    @Override
    public void end(boolean interrupted) {
        elevator.musicstop();
        System.out.println("musiceEnd");

    }
    
//----
//This is my first line of code - Ayden Dudley
}