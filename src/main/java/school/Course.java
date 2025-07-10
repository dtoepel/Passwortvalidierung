package school;

public class Course {
    private final String raum;
    private final String dozent;
    private final String name;

    public Course(String raum, String dozent, String name) {
        this.raum = raum;
        this.dozent = dozent;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "raum='" + raum + '\'' +
                ", dozent='" + dozent + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
