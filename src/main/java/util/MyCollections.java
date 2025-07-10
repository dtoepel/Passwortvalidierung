package util;

import school.Course;
import school.School;
import school.Student;

public class MyCollections {

    public static void main(String[] args) {
        School school = new School();
        Student alice = new Student("Alice", "Washington");
        Student bob = new Student("Bob", "Adams");
        school.addStudent(alice);
        school.addStudent(bob);
        school.addStudent(new Student("Charlie", "Jefferson"));
        school.addStudent(new Student("Chelsea", "Jenkins"));

        Course coursePainting = new Course("-1603", "Bob Ross", "The Joy of Painting");
        alice.addCourse(coursePainting);
        bob.addCourse(coursePainting);

        System.out.println("get student by id: " + school.getStudent(alice.getStudentID()));
        System.out.println("students for painting course: " + school.getStudents(coursePainting));
        school.removeStudent(alice);
        System.out.println("Alice removed!");
        System.out.println("get student by id: " + school.getStudent(alice.getStudentID()));
        System.out.println("students for painting course: " + school.getStudents(coursePainting));


    }
}
