public class Patient implements Comparable<Patient> {
    private int id;
    private String name;
    private int priority;

    static int count = 0;

    public Patient(String name, int priority) {
        this.id = ++count;
        this.name = name;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setPriority(int p) {
        priority = p;
    }

    @Override
    public String toString() {
        return "\t" + id + "):  " + name + "(priority:" + priority + ")";
    }

    @Override
    public int compareTo(Patient patient) {
        return this.priority - patient.priority;
    }
}