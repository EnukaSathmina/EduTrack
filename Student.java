// Student.java
// This class represents a Student with ID, name, and marks for 3 subjects.
// It provides methods to calculate average marks and determine grade.

public class Student {
    private int id;
    private String name;
    private double[] marks; // Array to hold marks for 3 subjects

    // Constructor to initialize a Student object
    public Student(int id, String name, double mark1, double mark2, double mark3) {
        this.id = id;
        this.name = name;
        this.marks = new double[]{mark1, mark2, mark3};
    }

    // Getter for ID
    public int getId() {
        return id;
    }

    // Getter for Name
    public String getName() {
        return name;
    }

    // Getter for Marks
    public double[] getMarks() {
        return marks;
    }

    // Method to calculate average marks
    public double getAverage() {
        double sum = 0;
        for (double mark : marks) {
            sum += mark;
        }
        return sum / marks.length;
    }

    // Method to determine grade based on average
    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) {
            return "A";
        } else if (avg >= 70) {
            return "B";
        } else if (avg >= 50) {
            return "C";
        } else {
            return "Fail";
        }
    }

    // Override toString for displaying student info
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Math: " + marks[0] + ", English: " + marks[1] + ", Science: " + marks[2] +
               ", Average: " + String.format("%.2f", getAverage()) + ", Grade: " + getGrade();
    }
}