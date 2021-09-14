package ca.bcit.comp2522.assignment9.question4;

public class Student implements Comparable<Student> {
    private final Integer id;
    private final String firstName;
    private final String lastName;

    public Student(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public int compareTo(Student o) {
        if (o.id.equals(this.id)) {
            return 0;
        } else if (this.id < o.id) {
            return -1;
        }
        return 1;
    }
}
