package records;

import util.MyList;

import java.util.Vector;

public record Zoo(MyList<Animal> animals) {

    public static void main(String[] args) {
        Species s1 = new Species("Double Headed Squirrel", 50);
        Owner o1 = new Owner("Boss of the Zoo", 40, new Address("221b Zoo Street", "91100", "Mount Zoo", "North Tacoma"));
        Animal a1a = new Animal("001a", "Joe", s1, 3, o1);
        Animal a1b = new Animal("001b", "Jane", s1, 3, o1);
        Animal a2 = new Animal("002", "William", new Species("Invisible Unicorn", 400), 7, o1);
        Animal a3 = new Animal("003", "Jack", new Species("Orange Skinned Baboon", 2500), 11, o1);
        Animal a4 = new Animal("004", "Averell", new Species("Swamp Goat", 300), 15, o1);
        Animal a5 = new Animal("005", "Amanda Miller", new Species("Naughty Kid", 1500), 13,
                new Owner("Mr. and Mrs. Miller", 42, new Address("123 Fake Street", "12345", "Exampletown", "Examplaria")));

        MyList<Animal> animals = new MyList<>();
        animals.add(a1a);
        animals.add(a1b);
        animals.add(a2);
        animals.add(a3);
        animals.add(a4);
        animals.add(a5);

        Zoo zoo = new Zoo(animals);

        System.out.println(a5);
        System.out.println(a5==new Animal(a5.id(),  a5.name(), a5.species(), a5.age(), a5.owner()));
        System.out.println(a5.equals(new Animal(a5.id(), a5.name(), a5.species(), a5.age(), a5.owner())));

        System.out.println(zoo.getFoodReq());
    }

    private double getFoodReq() {
        double food = 0;
        for(Animal animal : animals) {
            food += animal.species().foodReqGrams();
        }
        return food;
    }

}
