package car;

import java.awt.*;

public class Vehicle {
    private final String brand;
    private final String model;
    private final Color color;
    private final int yearOfManufacture;
    private final double maxSpeed;
    private String registration;

    private boolean running;
    private double currentSpeed;

    public Vehicle(String brand, String model, Color color, int yearOfManufacture, double maxSpeed) {
        this(brand, model, color, yearOfManufacture, maxSpeed, "");
    }

    public Vehicle(String brand, String model, Color color, int yearOfManufacture, double maxSpeed, String registration) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.yearOfManufacture = yearOfManufacture;
        this.maxSpeed = maxSpeed;
        this.running = false;
        this.currentSpeed = 0;
        this.setRegistration(registration);
    }

    public void setRegistration(String registration) {
        if(registration == null) throw new NullPointerException("Registration is null");
        if(registration.matches("^[A-ZÄÖÜ]{1,3}-[A-Z]{1,2} [1-9][0-9]{0,3}$") && registration.length() <= 10) {
            this.registration = registration;
        } else {
            throw new IllegalArgumentException("Invalid registration");
        }
    }

    public void start() {
        if(running) throw new IllegalStateException("car.Car ("+registration+") is already running");
        if("".equals(registration)) throw new IllegalArgumentException("Must not run vehicle without registration");
        this.running = true;
        System.out.println("car.Vehicle ("+registration+") started");
    }

    public void accelerate() {
        if(!running) throw new IllegalStateException("car.Vehicle ("+registration+") is not running");
        if(this.currentSpeed == this.maxSpeed) throw new IllegalStateException("car.Vehicle ("+registration+"): Max speed exceeded");
        this.currentSpeed += 10;
        if(this.currentSpeed > this.maxSpeed) this.currentSpeed = this.maxSpeed;
        System.out.println("car.Vehicle ("+registration+") accelerated to " + this.currentSpeed + "km/h.");
    }

    public void printInfo() {
        System.out.println("This vehicle ("+registration+") is a " + brand + " " + model +
                " from " + yearOfManufacture + " with a maximum speed of " + maxSpeed + ".");
    }

    public String getBrand() {return brand;}
    public String getModel() {return model;}
    public Color getColor() {return color;}
    public int getYearOfManufacture() {return yearOfManufacture;}
    public String getRegistration() {return registration;}
    public boolean isRunning() {return running;}
    public double getCurrentSpeed() {return currentSpeed;}
    public double getMaxSpeed() {return maxSpeed;}
}
