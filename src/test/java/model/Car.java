package model;

import java.util.List;

public class Car {
    public String brand;
    public String model;
    public int year;
    public String bodyType;
    public String driveType;
    public Engine engine;
    public Acceleration acceleration;
    public Battery battery;
    public Interior interior;
    public Suspension suspension;
    public String price;


    public static class Engine {
        public String type;
        public String power;
        public String torque;
    }

    public static class Acceleration {
        public String zeroToHundred;
        public String topSpeed;
    }

    public static class Battery {
        public String capacity;
        public String range;
    }

    public static class Interior {
        public int seats;
        public List<String> driverAssist;
    }

    public static class Suspension {
        public boolean adaptiveAirSuspension;
    }
}
