public class Student implements Comparable<Student> {
    private int id;
    private String lastName;
    private String firstName;
    private String major;

    public Student(int id, String lastName, String firstName, String major) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.major = major;
    }

    public int getId()          {return id; }

    public String getLastName() { return lastName;}
    public String getFirstName() {return firstName;}
    public String getMajor() { return major; }

    @Override
    public String toString() {
        return String.format("%d - %s, %s (%s)", id, lastName, firstName, major);
    }

    @Override
    public int compareTo(Student other) {
        int lastCompare = this.lastName.compareToIgnoreCase(other.lastName);
        if (lastCompare != 0) return lastCompare;
        return this.firstName.compareToIgnoreCase(other.firstName);
    }
}

 