package frc.robot;

import frc.robot.commands.DrivetrainCommands.ZeroGyro;
import frc.robot.commands.ElevatorCommands.ElevatorResetEncoder;
import frc.robot.commands.IntakeCommands.IntakeMotorGo;
import frc.robot.commands.IntakeCommands.IntakeMotorStop;
import frc.robot.commands.IntakeCommands.ScoreWithTrigger;
import frc.robot.subsystems.*;
//import frc.robot.
import frc.robot.subsystems.drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import frc.robot.commands.LEDCommands.Purple;
import frc.robot.commands.LEDCommands.Yellow;
import frc.robot.commands.SmartCommands.CollectFloorPoseBack;
import frc.robot.commands.SmartCommands.CollectFloorPoseParallelWithSequentialIntake;
import frc.robot.commands.SmartCommands.CollectSinglePoseParallelWithSequentialIntake;
import frc.robot.commands.SmartCommands.HomePoseParallel;
import frc.robot.commands.SmartCommands.Collects.CollectUprightPose;
import frc.robot.commands.SmartCommands.Scores.ArmDownToScoreConeHigh;
import frc.robot.commands.SmartCommands.Scores.ScoreHighConePoseFast;
import frc.robot.commands.SmartCommands.Scores.ScoreHighCubePoseFast;
import frc.robot.commands.SmartCommands.Scores.ScoreMiddleConePose;
import frc.robot.commands.SmartCommands.Scores.ScoreMiddleCubePoseParallel;
import frc.robot.commands.SmartCommands.Auto.PickupCubeRedLoadingStationv2;
import frc.robot.commands.SmartCommands.Auto.RedHighCubeAndTaxiAndPickupAndEngage;
import frc.robot.commands.SmartCommands.Auto.RedHighCubeAndTaxiAndPickupAndEngageCubeShoot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.DrivetrainCommands.DriveToTarget;
import frc.robot.commands.DrivetrainCommands.DriveWithSlow;
import frc.robot.commands.DrivetrainCommands.Reset;
import frc.robot.commands.SmartCommands.Auto.AutoDrive;
import frc.robot.commands.SmartCommands.Auto.BlueHighCubeAndTaxiAndPickupAndEngage;
import frc.robot.commands.SmartCommands.Auto.BlueHighCubeAndTaxiAndPickupAndEngageCubeShoot;
import frc.robot.commands.SmartCommands.Auto.HighConeAndTaxiAndPickupAndEngage;
import frc.robot.commands.SmartCommands.Auto.HighConeBackup;
import frc.robot.commands.SmartCommands.Auto.HighCube;
import frc.robot.commands.SmartCommands.Auto.HighCubeAndEngageAuto;
import frc.robot.commands.SmartCommands.Auto.HighCubeAndTaxiThenEngage;
import frc.robot.commands.SmartCommands.Auto.PickupCubeBlueLoadingStationv2;
import frc.robot.commands.SmartCommands.ClimbPoseBack;

public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();
  
    public final intake m_intake = new intake();
    public final wrist m_wrist = new wrist();
    public final elevator m_elevator = new elevator();
    public final arm m_arm = new arm();
    public final leds m_leds = new leds();

    private DoubleSupplier translationSup;
    private DoubleSupplier strafeSup;
    private DoubleSupplier rotationSup;
    private BooleanSupplier robotCentricSup;


// Joysticks
private final XboxController xboxController1 = new XboxController(1);
private final XboxController xboxController0 = new XboxController(0);


  
  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();


  private final Joystick driver = new Joystick(0);

  /* Drive Controls */
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int strafeAxis = XboxController.Axis.kLeftX.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;


  private final int driverRightTrig = XboxController.Axis.kRightTrigger.value;

  /* Driver Buttons */
  private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kStart.value);
  private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftStick.value);

  public final drivetrain m_drivetrain = new drivetrain();




  private RobotContainer() {
  
    // Configure the button bindings

    
    SmartDashboard.putData("Reset ElevatorEncoder", new ElevatorResetEncoder(m_elevator));
    SmartDashboard.putData("ScoreHighCubeFast :)", new ScoreHighCubePoseFast(m_intake, m_wrist, m_arm, m_elevator));
    SmartDashboard.putData("ScoreHighCONEFast >:)", new ScoreHighConePoseFast(m_intake, m_wrist, m_arm, m_elevator));
    SmartDashboard.putData("SHOOT DA CUBE", new IntakeMotorGo(m_intake, -1));
    SmartDashboard.putData("DriveToTarget", new DriveToTarget(m_drivetrain, m_drivetrain.getPose(), new Pose2d(new Translation2d(3,3), new Rotation2d(0)), 0.3));
    //SmartDashboard.putData("reset odometry", new ResetOdometry(m_drivetrain));


    

    configureButtonBindings();

    // Configure default commands

    m_intake.setDefaultCommand(new ScoreWithTrigger(m_intake, xboxController0));
   //m_wrist.setDefaultCommand(new WristMoveOne(m_wrist, xboxController1)); //xboxController1 right Y
   // m_elevator.setDefaultCommand(new ElevatorTune( m_elevator ));
   //-----I don't think we need this drive default command anymore-----//
   // m_drivetrain.setDefaultCommand(new Drive( m_drivetrain, translationSup, strafeSup, rotationSup, robotCentricSup));
    //m_arm.setDefaultCommand(new ArmHold(m_arm));
    //m_leds.setDefaultCommand(new SpiritColors(m_leds));

    m_drivetrain.setDefaultCommand(
            new DriveWithSlow(
                m_drivetrain, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean(),
                xboxController0

            )
        );
        
    
        
    // Configure autonomous sendable chooser
      
    m_chooser.setDefaultOption("HighCubeTaxiThenEngage", new HighCubeAndTaxiThenEngage(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("HighCube", new HighCube(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("HighConeBackup", new HighConeBackup(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("MEGA_SENDIT-Cube shoot auto   BLUE", new BlueHighCubeAndTaxiAndPickupAndEngageCubeShoot(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("MEGA_SENDIT-Cube shoot auto   RED", new RedHighCubeAndTaxiAndPickupAndEngageCubeShoot(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));

    m_chooser.addOption("AutoDrive", new AutoDrive(m_leds, m_drivetrain));
    m_chooser.addOption("HighCubeAndEngage", new HighCubeAndEngageAuto(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    //add blue far, red, and red far
    m_chooser.addOption("HighCubeTaxiThenEngage", new HighCubeAndTaxiThenEngage(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("PickupCubeBLUELoadingStationV2", new PickupCubeBlueLoadingStationv2(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("PickupCubeREDLoadingStationV2", new PickupCubeRedLoadingStationv2(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("YOLO-HighConeAndTaxiAndPickupAndEngage", new HighConeAndTaxiAndPickupAndEngage(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("SENDIT-RedHighCubeAndTaxiAndPickupAndEngage", new RedHighCubeAndTaxiAndPickupAndEngage(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    m_chooser.addOption("SENDIT-BlueHighCubeAndTaxiAndPickupAndEngage", new BlueHighCubeAndTaxiAndPickupAndEngage(m_intake, m_wrist, m_arm, m_elevator, m_drivetrain, m_leds));
    //m_chooser.addOption("DriveToTarget", new DriveToTarget(m_drivetrain, poses, translationAxis, driverRightTrig));
    SmartDashboard.putData("Auto Mode", m_chooser);
 
  }


  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  

  private void configureButtonBindings() {


//------------------DRIVER CONTROLLER-------------------------//

final JoystickButton xboxButtonB = new JoystickButton(xboxController0, XboxController.Button.kB.value);
final JoystickButton xboxButtonA = new JoystickButton(xboxController0, XboxController.Button.kA.value);  
final JoystickButton xboxButtonY = new JoystickButton(xboxController0, XboxController.Button.kY.value);
final JoystickButton xboxButtonX = new JoystickButton(xboxController0, XboxController.Button.kX.value);
final JoystickButton xboxButtonLB = new JoystickButton(xboxController0, XboxController.Button.kLeftBumper.value); 
final JoystickButton xboxButtonRB = new JoystickButton(xboxController0, XboxController.Button.kRightBumper.value);
final JoystickButton xboxButtonStart = new JoystickButton(xboxController0, XboxController.Button.kStart.value);
final JoystickButton xboxButtonSelect = new JoystickButton(xboxController0, XboxController.Button.kBack.value);  

//xboxButtonB.onTrue(new CollectLoadingStationPose(m_elevator, m_intake, m_wrist, m_arm, m_leds));
xboxButtonA.onTrue(new CollectFloorPoseParallelWithSequentialIntake(m_elevator, m_intake, m_wrist, m_arm, m_leds));
xboxButtonY.onTrue(new HomePoseParallel(m_elevator, m_intake, m_wrist, m_arm));
xboxButtonB.onTrue(new CollectUprightPose(m_elevator, m_intake, m_wrist, m_arm, m_leds));  
xboxButtonX.onTrue(new CollectSinglePoseParallelWithSequentialIntake(m_elevator, m_intake, m_wrist, m_arm, m_leds));   
xboxButtonLB.onTrue(new ClimbPoseBack(m_elevator, m_intake, m_wrist, m_arm));
xboxButtonRB.onTrue(new IntakeMotorGo(m_intake, -0.2));
xboxButtonStart.onTrue(new ZeroGyro(m_drivetrain));
xboxButtonSelect.onTrue(new CollectFloorPoseBack(m_elevator, m_intake, m_wrist, m_arm, m_leds));


 //------------------------OPERATOR CONTROLLER----------------------//

final JoystickButton xbox2ButtonA = new JoystickButton(xboxController1, XboxController.Button.kA.value);     
final JoystickButton xbox2ButtonY = new JoystickButton(xboxController1, XboxController.Button.kY.value); 
final JoystickButton xbox2ButtonX = new JoystickButton(xboxController1, XboxController.Button.kX.value);
final JoystickButton xbox2ButtonB = new JoystickButton(xboxController1, XboxController.Button.kB.value);          
final JoystickButton xbox2ButtonLB = new JoystickButton(xboxController1, XboxController.Button.kLeftBumper.value);
final JoystickButton xbox2ButtonRB = new JoystickButton(xboxController1, XboxController.Button.kRightBumper.value);
final JoystickButton xbox2ButtonStart = new JoystickButton(xboxController1,XboxController.Button.kStart.value);
final JoystickButton xbox2ButtonSelect = new JoystickButton(xboxController1,XboxController.Button.kBack.value);
final POVButton xbox2downPOVButton = new POVButton(xboxController1, 180);

xbox2ButtonX.onTrue(new ScoreMiddleConePose(m_intake, m_wrist, m_arm, m_elevator));
xbox2ButtonY.onTrue(new ScoreHighConePoseFast(m_intake, m_wrist, m_arm, m_elevator));
xbox2ButtonA.onTrue(new ScoreMiddleCubePoseParallel(m_intake, m_wrist, m_arm, m_elevator));
xbox2ButtonB.onFalse(new ScoreHighCubePoseFast(m_intake, m_wrist, m_arm, m_elevator));
xbox2ButtonLB.onTrue(new IntakeMotorStop(m_intake));
xbox2ButtonStart.onTrue(new Purple(m_leds));
xbox2ButtonSelect.onTrue(new Yellow(m_leds));
xbox2ButtonRB.onTrue(new Reset(m_drivetrain));
xbox2downPOVButton.onTrue(new ArmDownToScoreConeHigh(m_intake, m_wrist, m_arm, m_elevator));





zeroGyro.onTrue(new ZeroGyro(m_drivetrain).withInterruptBehavior(InterruptionBehavior.kCancelSelf));

  }

    
public XboxController getxboxController0() {
      return xboxController0;
    }

public XboxController getxboxController1() {
      return xboxController1;
    }


    

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

