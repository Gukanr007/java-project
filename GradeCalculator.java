import java.util.ArrayList;
import java.util.Scanner;

public class GradeCalculator {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Student> students = new ArrayList<>();
    private static String[] subjectNames;

    public static void main(String[] args) {
        System.out.println("\n===== STUDENT GRADE CALCULATOR =====\n");
        
        setupSubjects();
        
        boolean continueCalculating = true;
        while (continueCalculating) {
            Student student = inputStudentData();
            students.add(student);
            displayStudentResult(student);
            
            System.out.print("\nDo you want to calculate grades for another student? (yes/no): ");
            String response = scanner.next().toLowerCase();
            continueCalculating = response.equals("yes") || response.equals("y");
            
            // Clear the buffer
            scanner.nextLine();
        }
        
        // Display summary for all students
        if (students.size() > 1) {
            displayAllStudentsSummary();
        }
        
        System.out.println("\nThank you for using the Student Grade Calculator!");
        scanner.close();
    }
    
    private static void setupSubjects() {
        int numSubjects;
        do {
            System.out.print("Enter the number of subjects (3-5): ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please enter a valid number (3-5): ");
                scanner.next(); // Clear invalid input
            }
            numSubjects = scanner.nextInt();
            scanner.nextLine(); // Clear buffer
        } while (numSubjects < 3 || numSubjects > 5);
        
        subjectNames = new String[numSubjects];
        
        System.out.println("\nEnter the name for each subject:");
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            subjectNames[i] = scanner.nextLine();
        }
    }
    
    private static Student inputStudentData() {
        System.out.println("\n----- Enter Student Details -----");
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        int[] marks = new int[subjectNames.length];
        
        for (int i = 0; i < subjectNames.length; i++) {
            int mark;
            do {
                System.out.print("Enter marks for " + subjectNames[i] + " (0-100): ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Please enter a valid mark (0-100): ");
                    scanner.next(); // Clear invalid input
                }
                mark = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } while (mark < 0 || mark > 100);
            
            marks[i] = mark;
        }
        
        return new Student(name, marks);
    }
    
    private static void displayStudentResult(Student student) {
        System.out.println("\n----- Student Grade Report -----");
        System.out.println("Name: " + student.getName());
        
        System.out.println("\nSubject-wise Marks:");
        System.out.println("---------------------------");
        for (int i = 0; i < subjectNames.length; i++) {
            System.out.printf("%-15s: %3d/100\n", subjectNames[i], student.getMarkForSubject(i));
        }
        System.out.println("---------------------------");
        
        System.out.printf("Total Marks     : %3d/%d\n", 
                        student.getTotal(), student.getNumberOfSubjects() * 100);
        System.out.printf("Average         : %.2f\n", student.getAverage());
        System.out.println("Grade           : " + student.getGrade());
        
        // Display comment based on grade
        System.out.print("Remarks         : ");
        switch (student.getGrade()) {
            case 'A':
                System.out.println("Excellent performance!");
                break;
            case 'B':
                System.out.println("Good performance!");
                break;
            case 'C':
                System.out.println("Average performance.");
                break;
            case 'D':
                System.out.println("Below average, needs improvement.");
                break;
            case 'F':
                System.out.println("Failed. Significant improvement needed.");
                break;
        }
    }
    
    private static void displayAllStudentsSummary() {
        System.out.println("\n\n========== SUMMARY OF ALL STUDENTS ==========");
        System.out.printf("%-20s %-10s %-10s %-6s\n", "Name", "Total", "Average", "Grade");
        System.out.println("------------------------------------------------");
        
        for (Student student : students) {
            System.out.printf("%-20s %-10d %-10.2f %-6c\n", 
                    student.getName(), 
                    student.getTotal(), 
                    student.getAverage(), 
                    student.getGrade());
        }
        System.out.println("------------------------------------------------");
    }
}