package pharmacy;

public class Medication {

    private final String name;
    private final double price;
    private final int available;

    public Medication(String name, double price, int available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {return name;}
    public double getPrice() {return price;}
    public int getAvailability() {return available;}

    @Override
    public String toString() {
        return "Medication{" +
                "name='" + name + '\'' +
                '}';
    }
}
