package pharmacy;

import java.util.HashMap;

public class Pharmacy {
    private final HashMap<String, Medication> medications = new HashMap<>();

    public int countMedTypes() {
        int result = 0;
        for(Medication medication : medications.values()) {
            result += medication.getAvailability()>0?1:0;
        }
        return result;
    }

    public int countTotalMed() {
        int result = 0;
        for(Medication medication : medications.values()) {
            result += medication.getAvailability();
        }
        return result;
    }

    public void save(Medication medication) {
        medications.put(medication.getName(), medication);
    }

    public Medication find(String name) {
        return medications.get(name);
    }

    public void delete(String name) {
        medications.remove(name);
    }

    public void printPharmacy() {
        for(Medication medication : medications.values()) {
            System.out.println(medication.getName()+ ": " + medication.getAvailability() + " available for " + medication.getPrice() + " DM");
        }
    }

    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.save(new Medication("Yellow pill", 3.99, 17));
        pharmacy.save(new Medication("Green pill", 4.99, 0));
        pharmacy.save(new Medication("Blue pill", 0.99, 2));
        pharmacy.save(new Medication("White pill with red dots", 5.99, 23));

        System.out.println(pharmacy.countMedTypes() + " types of medications available");
        System.out.println(pharmacy.countTotalMed() + " medications available");
        System.out.println("find red:" + pharmacy.find("Red pill"));
        System.out.println("find yellow:" + pharmacy.find("Yellow pill"));
        System.out.println("find blue:" + pharmacy.find("Blue pill"));
        pharmacy.delete("Blue pill");
        System.out.println("Blue pill removed");
        System.out.println("find red:" + pharmacy.find("Red pill"));
        System.out.println("find yellow:" + pharmacy.find("Yellow pill"));
        System.out.println("find blue:" + pharmacy.find("Blue pill"));
        System.out.println(pharmacy.countMedTypes() + " types of medications available");
        System.out.println(pharmacy.countTotalMed() + " medications available");

        pharmacy.printPharmacy();
    }
}
