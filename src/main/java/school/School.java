package school;

import util.MyList;

import java.util.ArrayList;

public class School {
    private final MyList<Student> students = new MyList<>();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Added student " + student);
        System.out.println("new student count: " + students.size());

    }

    public Student getStudent(String id) {
        for(Student student : students){
            if(student.getStudentID().equals(id))
                return student;
        }
        return null;
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public ArrayList<Student> getStudents(Course course) {
        ArrayList<Student> results = new  ArrayList<>();
        for(Student student : students){
            if(student.hasCourse(course))
                results.add(student);
        }
        return results;
    }
}
