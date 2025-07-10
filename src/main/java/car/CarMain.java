package car;

import java.awt.*;

public class CarMain {
    public static void main(String[] args) {
        Car car1 = new Car("DeLorean", "DMC-12", Color.GRAY, 1981, 141.6, 3, "T-QY 901");
        Vehicle car2 = new Vehicle("DeLorean", "DMC-12", Color.GRAY, 1981, 141.6, "T-QY 902");

        car1.start();
        for(int i = 0; i < 16; i++) {
            try{
                car1.accelerate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        car2.start();
        for(int i = 0; i < 16; i++) {
            try{
                car2.accelerate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        car1.printInfo();
        car2.printInfo();
    }
}
