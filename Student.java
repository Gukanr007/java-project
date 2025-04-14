public class Student {
    private String name;
    private int[] marks;
    private int total;
    private double average;
    private char grade;

    // Constructor
    public Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
        calculateTotal();
        calculateAverage();
        calculateGrade();
    }

    // Calculate the total marks
    private void calculateTotal() {
        total = 0;
        for (int mark : marks) {
            total += mark;
        }
    }

    // Calculate the average marks
    private void calculateAverage() {
        average = (double) total / marks.length;
    }

    // Calculate the grade based on average
    private void calculateGrade() {
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    // Get marks for a specific subject
    public int getMarkForSubject(int index) {
        return marks[index];
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getTotal() {
        return total;
    }

    public double getAverage() {
        return average;
    }

    public char getGrade() {
        return grade;
    }

    public int getNumberOfSubjects() {
        return marks.length;
    }
}
