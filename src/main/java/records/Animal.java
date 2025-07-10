package records;

public record Animal(String id, String name, Species species, int age, Owner owner) {

    public Animal withId(String id) {return new Animal(id, name, species, age, owner);}
    public Animal withName(String name) {return new Animal(id, name, species, age, owner);}
    public Animal withSpecies(Species species) {return new Animal(id, name, species, age, owner);}
    public Animal withAge(int age) {return new Animal(id, name, species, age, owner);}
    public Animal withOwner(Owner owner) {return new Animal(id, name, species, age, owner);}
}
