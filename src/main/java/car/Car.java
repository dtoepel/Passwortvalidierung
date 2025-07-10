package car;

import java.awt.*;

public class Car extends Vehicle {

    private final int numberOfDoors;

    public Car(String brand, String model, Color color, int yearOfManufacture, double maxSpeed, int numberOfDoors) {
        super(brand, model, color, yearOfManufacture, maxSpeed);
        this.numberOfDoors = numberOfDoors;
    }

    public Car(String brand, String model, Color color, int yearOfManufacture, double maxSpeed, int numberOfDoors, String registration) {
        super(brand, model, color, yearOfManufacture, maxSpeed, registration);
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("It has " + numberOfDoors + " doors.");
    }
}
