



// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: RobotContainer.

package frc.robot;

//import frc.robot.commands.*;
import frc.robot.commands.ArmCommands.ArmGo;
import frc.robot.commands.ArmCommands.ArmStop;
import frc.robot.commands.ArmCommands.ArmHold;
import frc.robot.commands.ArmCommands.ArmMoveHome;
import frc.robot.commands.ArmCommands.ArmJoystick;
import frc.robot.commands.ArmCommands.ArmMoveFront;
import frc.robot.commands.ArmCommands.VelocityMode;
//import frc.robot.commands.ArmCommands.ArmMotorLeadGo;
//import frc.robot.commands.ArmCommands.ArmMotorLeadStop;

import frc.robot.commands.DrivetrainCommands.JoystickDrive;
import frc.robot.commands.ElevatorCommands.ElevatorManual;
import frc.robot.commands.ElevatorCommands.ElevatorMotorGo;
import frc.robot.commands.ElevatorCommands.ElevatorMotorGoToPosition;
import frc.robot.commands.ElevatorCommands.ElevatorMotorStop;
import frc.robot.commands.ExampleCommands.AutonomousCommand;
import frc.robot.commands.ExampleCommands.ExampleCommand;
import frc.robot.commands.ExampleCommands.ExampleInstantCommand;
import frc.robot.commands.ExampleCommands.ExampleSequentialCommandGroup;
import frc.robot.commands.IntakeCommands.IntakeDoNothing;
import frc.robot.commands.IntakeCommands.IntakeMotorLeftGo;
//import frc.robot.commands.IntakeCommands.IntakeMotorLeftGoLoop;
import frc.robot.commands.IntakeCommands.IntakeMotorLeftStop;
import frc.robot.commands.IntakeCommands.IntakeMotorRightGo;
//import frc.robot.commands.IntakeCommands.IntakeMotorRightGoLoop;
import frc.robot.commands.IntakeCommands.IntakeMotorRightStop;
//import frc.robot.commands.IntakeCommands.IntakeMotorSqueezeGo;
import frc.robot.commands.IntakeCommands.Squeeze;
import frc.robot.commands.IntakeCommands.Release;
//import frc.robot.commands.IntakeCommands.IntakeMotorSqueezeStop;
import frc.robot.commands.IntakeCommands.SmartCollect;
//import frc.robot.commands.WristCommands.WristManual;
import frc.robot.commands.WristCommands.WristGo;
//import frc.robot.commands.WristCommands.WristMotorGoToPosition;
import frc.robot.commands.WristCommands.WristStop;
import frc.robot.commands.WristCommands.WristHold;
import frc.robot.commands.WristCommands.WristMoveBack;
import frc.robot.commands.WristCommands.WristMoveHome;
import frc.robot.commands.WristCommands.WristMoveTop;
import frc.robot.subsystems.*;
import frc.robot.subsystems.drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import frc.robot.commands.DrivetrainCommands.JoystickDrive.*;


import frc.robot.commands.LEDCommands.SpiritColors;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;


import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;




import frc.robot.subsystems.*;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
// The robot's subsystems
    public final intake m_intake = new intake();
    public final wrist m_wrist = new wrist();
    public final elevator m_elevator = new elevator();
    public final arm m_arm = new arm();
    public final drivetrain m_drivetrain = new drivetrain();
    public final leds m_leds = new leds();

    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;


// Joysticks
private final XboxController xboxController2 = new XboxController(1);
private final XboxController xboxController1 = new XboxController(0);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Smartdashboard Subsystems


    // SmartDashboard Buttons
    SmartDashboard.putData("AutonomousCommand", new AutonomousCommand());
    SmartDashboard.putData("ExampleCommand", new ExampleCommand( m_wrist ));
    SmartDashboard.putData("ExampleSequentialCommandGroup", new ExampleSequentialCommandGroup( m_wrist ));
    SmartDashboard.putData("ExampleInstantCommand", new ExampleInstantCommand( m_wrist ));
    SmartDashboard.putData("ArmGo", new ArmGo( m_arm ));
    SmartDashboard.putData("ArmHold", new ArmHold( m_arm ));
    SmartDashboard.putData("ArmHome", new ArmMoveHome( m_arm ));
    SmartDashboard.putData("ArmJoystick", new ArmJoystick( m_arm ));
    SmartDashboard.putData("ArmPosA", new ArmMoveFront( m_arm ));
    SmartDashboard.putData("ArmStop", new ArmStop( m_arm ));
    SmartDashboard.putData("VelocityMode", new VelocityMode( m_arm ));
    SmartDashboard.putData("ElevatorMotorGo", new ElevatorMotorGo( m_elevator ));
    SmartDashboard.putData("ElevatorMotorStop", new ElevatorMotorStop( m_elevator ));
    SmartDashboard.putData("WristMotorGo", new WristGo( m_wrist ));
    SmartDashboard.putData("WristMotorStop", new WristStop( m_wrist ));
    SmartDashboard.putData("IntakeMotorLeftGo", new IntakeMotorLeftGo( m_intake ));
    SmartDashboard.putData("IntakeMotorLeftStop", new IntakeMotorLeftStop( m_intake ));
    SmartDashboard.putData("IntakeMotorRightGo", new IntakeMotorRightGo( m_intake ));
    SmartDashboard.putData("IntakeMotorRightStop", new IntakeMotorRightStop( m_intake ));
    SmartDashboard.putData("Squeeze", new Squeeze( m_intake ));
    SmartDashboard.putData("Release", new Release( m_intake ));
    SmartDashboard.putData("ElevatorMotorGoToPosition", new ElevatorMotorGoToPosition( m_elevator ));
    //SmartDashboard.putData("WristMotorGoToPosition", new WristMotorGoToPosition( m_wrist ));
   // SmartDashboard.putData("IntakeMotorLeftGoLoop", new IntakeMotorLeftGoLoop( m_intake ));
    //SmartDashboard.putData("IntakeMotorRightGoLoop", new IntakeMotorRightGoLoop( m_intake ));
    SmartDashboard.putData("IntakeMotorSqueezeGoToPosition", new Squeeze( m_intake ));
    SmartDashboard.putData("SmartCollect", new SmartCollect( m_intake ));
    SmartDashboard.putData("JoystickDrive", new JoystickDrive( m_drivetrain, translationSup, strafeSup, rotationSup, robotCentricSup));
    SmartDashboard.putData("ElevatorManual", new ElevatorManual( m_elevator ));
    //SmartDashboard.putData("WristManual", new WristManual( m_wrist ));
    SmartDashboard.putData("IntakeDoNothing", new IntakeDoNothing( m_intake ));

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SMARTDASHBOARD
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND
    m_intake.setDefaultCommand(new IntakeDoNothing( m_intake ));
    //m_wrist.setDefaultCommand(new WristManual( m_wrist ));
    m_elevator.setDefaultCommand(new ElevatorManual( m_elevator ));
    m_drivetrain.setDefaultCommand(new JoystickDrive( m_drivetrain, translationSup, strafeSup, rotationSup, robotCentricSup));
    m_arm.setDefaultCommand(new ArmJoystick( m_arm ));
    m_leds.setDefaultCommand(new SpiritColors(m_leds));
    
    


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=SUBSYSTEM_DEFAULT_COMMAND

    // Configure autonomous sendable chooser
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    m_chooser.setDefaultOption("AutonomousCommand", new AutonomousCommand());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
// Create some buttons
final JoystickButton xboxButton2 = new JoystickButton(xboxController2, XboxController.Button.kA.value);        
xboxButton2.onTrue(new WristGo( m_wrist ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        
final JoystickButton xboxButton1 = new JoystickButton(xboxController1, XboxController.Button.kA.value);        
xboxButton1.onTrue(new ArmGo( m_arm ).withInterruptBehavior(InterruptionBehavior.kCancelSelf));
                        


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=BUTTONS
  }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
public XboxController getXboxController1() {
      return xboxController1;
    }

public XboxController getXboxController2() {
      return xboxController2;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }
  

}
