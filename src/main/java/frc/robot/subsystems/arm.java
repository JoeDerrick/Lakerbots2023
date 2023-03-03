// RobotBuilder Version: 5.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;


import frc.robot.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxAlternateEncoder;
import frc.robot.SetPoints;



/**
 *
 */
public class arm extends SubsystemBase {
    private CANSparkMax armMotorLead;
    private CANSparkMax armMotorFollow;
    public RelativeEncoder magEncoder;
    //private DigitalInput magEncoder; <---WRONG!
    private SparkMaxPIDController m_pidController;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
    //public double armPosA;
    //public double armHome;
    
    
    public double 
    armHome, armFront, armBack,
    armPlaceHybridFront, armPlaceHybridBack,
    armPlaceConeMiddleFront, armPlaceConeMiddleBack, armPlaceCubeMiddleFront, armPlaceCubeMiddleBack,
    armPlaceConeHighFront, armPlaceConeHighBack, armPlaceCubeHighFront, armPlaceCubeHighBack;


    public double setPoint, processVariable; /// made the setpoint public 
    boolean mode = SmartDashboard.getBoolean("Mode", false); // made this public
    public double inPositionThreshold = 1.0; //Allowed distance of error for position settings
    private static final SparkMaxAlternateEncoder.Type kAltEncType = SparkMaxAlternateEncoder.Type.kQuadrature;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    /**
    *
    */
public arm() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
armMotorLead = new CANSparkMax(12, MotorType.kBrushless);
 

armMotorFollow = new CANSparkMax(13, MotorType.kBrushless);
 

//magEncoder = new DigitalInput(1);
 //addChild("magEncoder", magEncoder);
 
 
 //magEncoder = armMotorLead.getAlternateEncoder(kAltEncType,1024);

 
armMotorLead.restoreFactoryDefaults();
armMotorFollow.restoreFactoryDefaults();

m_pidController = armMotorLead.getPIDController();
magEncoder = armMotorLead.getEncoder();

armMotorLead.setIdleMode(IdleMode.kBrake);
armMotorFollow.setIdleMode(IdleMode.kBrake);

armMotorLead.setInverted(false);
//armMotorFollow.setInverted(true);//True? TBD based on wiring


armMotorFollow.follow(armMotorLead, true);


// PID coefficients
kP = 5e-5;
kI = 1e-6;
kD = 0.0; 
kIz = 0; 
kFF = 1/5719; 
kMaxOutput = 1; 
kMinOutput = -1;
maxRPM = 5700;

armHome = SetPoints.armHome;
armFront = SetPoints.armFront;
armBack = SetPoints.armBack;
armPlaceHybridFront = SetPoints.armPlaceHybridFront;
armPlaceHybridBack = SetPoints.armPlaceHybridBack;
armPlaceConeMiddleFront = SetPoints.armPlaceConeMiddleFront;
armPlaceConeMiddleBack = SetPoints.armPlaceConeMiddleBack;
armPlaceCubeMiddleFront = SetPoints.armPlaceCubeMiddleFront;
armPlaceCubeMiddleBack = SetPoints.armPlaceCubeMiddleBack;
armPlaceConeHighFront = SetPoints.armPlaceConeHighFront;
armPlaceConeHighBack = SetPoints.armPlaceConeHighBack;
armPlaceCubeHighFront = SetPoints.armPlaceCubeHighFront;
armPlaceCubeHighBack = SetPoints.armPlaceCubeHighBack;

// Smart Motion Coefficients
maxVel = 6000; // rpm
maxAcc = 3000;

// set PID coefficients
m_pidController.setP(kP);
m_pidController.setI(kI);
m_pidController.setD(kD);

m_pidController.setIZone(kIz);
m_pidController.setFF(kFF);
m_pidController.setOutputRange(kMinOutput, kMaxOutput);

/**
 * Smart Motion coefficients are set on a SparkMaxPIDController object
 * 
 * - setSmartMotionMaxVelocity() will limit the velocity in RPM of
 * the pid controller in Smart Motion mode
 * - setSmartMotionMinOutputVelocity() will put a lower bound in
 * RPM of the pid controller in Smart Motion mode
 * - setSmartMotionMaxAccel() will limit the acceleration in RPM^2
 * of the pid controller in Smart Motion mode
 * - setSmartMotionAllowedClosedLoopError() will set the max allowed
 * error for the pid controller in Smart Motion mode
 */
int smartMotionSlot = 0;
m_pidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
m_pidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
m_pidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
m_pidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

// display PID coefficients on SmartDashboard
/* meehh let's not
SmartDashboard.putNumber("P Gain", kP);
SmartDashboard.putNumber("I Gain", kI);
SmartDashboard.putNumber("D Gain", kD);
SmartDashboard.putNumber("I Zone", kIz);
SmartDashboard.putNumber("Feed Forward", kFF);
SmartDashboard.putNumber("Max Output", kMaxOutput);
SmartDashboard.putNumber("Min Output", kMinOutput);
*/
// display Smart Motion coefficients
/* crowded dashboard
SmartDashboard.putNumber("Max Velocity", maxVel);
SmartDashboard.putNumber("Min Velocity", minVel);
SmartDashboard.putNumber("Max Acceleration", maxAcc);
SmartDashboard.putNumber("Allowed Closed Loop Error", allowedErr);
SmartDashboard.putNumber("Set Position", magEncoder.getPosition());
//SmartDashboard.putNumber("Set Velocity this one", magEncoder.getVelocity());

*/
// button to toggle between velocity and smart motion modes
SmartDashboard.putBoolean("Mode", true);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORarS
}
public void armHold(){
    setPoint = setPoint; // <-- not needed?
    m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
}

public void armJoystick(double y){
    armMotorLead.set(y); // slowed down arm for testing
    //armMotorFollow.set(y*0.25); //^
}

public void armStop(){
    armMotorLead.set(0);
    armMotorFollow.set(0);
}

public void armGo(){
    armMotorLead.set(0.6);
    armMotorFollow.set(0.6);
}
public double armGetCurrent(){
    return armMotorLead.getOutputCurrent();
}
/* 
public void velocityMode(){
    setPoint = SmartDashboard.getNumber("Set Velocity", 0);
    m_pidController.setReference(setPoint, CANSparkMax.ControlType.kVelocity);
    processVariable = magEncoder.getVelocity();
}
*/

public void armGoToPosition(Double position){
    setPoint = position;
    m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
}
public double armGetPosition(){
    double processVariable = magEncoder.getPosition();
    return processVariable;
}

public boolean armAtTargetPosition(){
    double currentPosition = magEncoder.getPosition();
    double targetPosition = setPoint;
    double positionError = Math.abs(targetPosition - currentPosition);
    if (positionError < inPositionThreshold) {
      return true;
    }
    else {
      return false;
    }
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        /* 
    double p = SmartDashboard.getNumber("P Gain", 0);
    double i = SmartDashboard.getNumber("I Gain", 0);
    double d = SmartDashboard.getNumber("D Gain", 0);
    double iz = SmartDashboard.getNumber("I Zone", 0);
    double ff = SmartDashboard.getNumber("Feed Forward", 0);
    double max = SmartDashboard.getNumber("Max Output", 0);
    double min = SmartDashboard.getNumber("Min Output", 0);
    double maxV = SmartDashboard.getNumber("Max Velocity", 0);
    double minV = SmartDashboard.getNumber("Min Velocity", 0);
    double maxA = SmartDashboard.getNumber("Max Acceleration", 0);
    double allE = SmartDashboard.getNumber("Allowed Closed Loop Error", 0);
   */

  // if PID coefficients on SmartDashboard have changed, write new values to controller
 
/*   if((p != kP)) { m_pidController.setP(p); kP = p; }
  if((i != kI)) { m_pidController.setI(i); kI = i; }
  if((d != kD)) { m_pidController.setD(d); kD = d; }
  if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
  if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
  if((max != kMaxOutput) || (min != kMinOutput)) { 
    m_pidController.setOutputRange(min, max); 
    kMinOutput = min; kMaxOutput = max; 
  }
  if((maxV != maxVel)) { m_pidController.setSmartMotionMaxVelocity(maxV,0); maxVel = maxV; }
  if((minV != minVel)) { m_pidController.setSmartMotionMinOutputVelocity(minV,0); minVel = minV; }
  if((maxA != maxAcc)) { m_pidController.setSmartMotionMaxAccel(maxA,0); maxAcc = maxA; }
  if((allE != allowedErr)) { m_pidController.setSmartMotionAllowedClosedLoopError(allE,0); allowedErr = allE; }
*/

  /* 
  SmartDashboard.putNumber("SetPoint", setPoint);
  SmartDashboard.putNumber("Process Variable", processVariable);
  SmartDashboard.putNumber("Output", armMotorLead.getAppliedOutput());
  SmartDashboard.putNumber("Position", magEncoder.getPosition());
  SmartDashboard.putNumber("Arm Velocity", magEncoder.getVelocity());
  SmartDashboard.putNumber("Amps yo", armGetCurrent());
  */

}

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

