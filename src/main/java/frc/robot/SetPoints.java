package frc.robot;

public final class SetPoints {
    
    //arm setPoints
    //NEGATIVE FOR ALL FRONT POSITIONS!!!
    public static double armHome = 0;
    public static double armFront = -25;
    public static double armBack = -25;
    public static double armPlaceHybridFront = -45;
    public static double armPlaceHybridBack = -100;
    public static double armPlaceConeMiddleFront = -23;
    public static double armPlaceConeMiddleBack = -250;
    public static double armPlaceCubeMiddleFront = 200;
    public static double armPlaceCubeMiddleBack = -200;
    public static double armPlaceConeHighFront = -23;
    public static double armPlaceConeHighBack = 25;
    public static double armPlaceCubeHighFront = -30;
    public static double armPlaceCubeHighBack = -300;
    public static double armPickupFront = -62;
    public static double armPickupBack = 55;
    public static double armPickupUprightCone = -50;

    public static double armLoadingStation = -20;



    //intake setPoints
    public static double intakeHome = 0;
    public static double intakeSqueezePos = 100;

    //Elevator setPoints

    //ALL ELEVATOR SETPOINTS NEED TO BE NEGATIVE!!!!!!!!!!!!!!!!
    public static double ElevatorHome = 0;
    public static double ElevatorMiddle = -20000 * 1.588235294;
    public static double ElevatorHigh = -75000 * 1.588235294;
    public static double ElevatorHighCube = -65000 * 1.588235294;
    public static double ElevatorLoadingStation = -35000 * 1.588235294;

    

    //wrist setpoints
    public static double WristHome = 0;
    public static double WristCollect = 30;
    public static double WristTop = 20;
    public static double WristBack = 35;
    public static double WristScoreFront = 5;
    public static double WristPickupUprightCone = 15;
    public static double WristLoadingStation = 0;
    public static double WristCharge = 40;
    public static double WristChargeBack = 13;



}
