package school;

import util.MyList;

import java.util.HashMap;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String studentID;
    private final static HashMap<String, String> studentIDs = new HashMap<>();
    private final MyList<Course> courses = new MyList<>();

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = getNewStudentID(firstName, lastName);
    }

    public static String getNewStudentID(String firstName, String lastName) {
        String s = firstName.substring(0,1).toUpperCase() + lastName.substring(0,2).toUpperCase();
        int i = 0;
        String id = "";
        String k = firstName + "-" +  lastName;
        do{
            i++;
            int p = ((int) s.charAt(0)) + (3 * (int) s.charAt(1)) + (5 * (int) s.charAt(2));
            p += (i/100) * 7;
            p += ((i/10)%10) * 11;
            p += (i%10) * 17;

            char c = (char) ((p%26) + 'A');
            id = "" + i;
            while(id.length() < 3) id = "0" + id;
            id = s + "-" + id + "-" + c;
        } while (studentIDs.values().contains(id));
        studentIDs.put(k, id);
        return id;
    }

    @Override
    public String toString() {
        return "school.Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", studentID='" + studentID + '\'' +
                '}';
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getStudentID() {return studentID;}

    public void addCourse(Course c) {
        courses.add(c);
    }

    public boolean hasCourse(Course course) {
        return courses.contains(course);
    }
}
