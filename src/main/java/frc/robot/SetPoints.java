package frc.robot;

public final class SetPoints {
    
    //arm setPoints
    //NEGATIVE FOR ALL FRONT POSITIONS!!!
    public static double armPickupFront = -58;//62
    public static double armPickupUprightCone = -48;
    public static double armPlaceHybridFront = -45;
    public static double armPlaceCubeHighFront = -30;
    public static double armFront = -25;
    public static double armBack = -25;
    public static double armPlaceConeMiddleFront = -23;
    public static double armPlaceConeHighFront = -23;
    public static double armLoadingStation = -20;
    public static double armHome = 0;
    public static double armPlaceConeHighBack = 25;
    public static double armPickupBack = 52;
    public static double armChargeBack = 55;


    //Elevator setPoints

    //ALL ELEVATOR SETPOINTS NEED TO BE NEGATIVE!!!!!!!!!!!!!!!!
    public static double ElevatorHome = 0;
    public static double ElevatorMiddle = -20000 * 1.588235294;
    public static double ElevatorHigh = -70000 * 1.588235294;
    public static double ElevatorMiddleCube = -15000*1.588 * 0;
    public static double ElevatorHighCube = -56000 * 1.588235294;
    public static double ElevatorLoadingStation = -40000 * 1.588235294;

    

    //wrist setpoints
    public static double WristHome = 0;
    public static double WristLoadingStation = 0;
    public static double WristScoreFront = 5;
    public static double WristScoreFrontCube = 10;
    public static double WristLoadingStationToGetPastBearing = 10;
    public static double WristChargeBack = 13;
    public static double WristPickupUprightCone = 10;
    public static double WristCollectBack = 21;
    public static double WristTop = 20;
    public static double WristCollect = 24;//30
    public static double WristBack = 35;
    public static double WristCharge = 40;




}
