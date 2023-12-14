// echo "# student-data" >> README.md
// git init
// git add St.java
// git commit -m "first commit"
// git branch -M main
// git remote add origin https://github.com/keshav2025/student-data.git
// git push -u origin main


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
class NegativeMarksException extends Exception {
    public NegativeMarksException(String message) {
        super(message);
    }
}

// Custom exception class for handling invalid roll numbers
class InvalidRollNumberException extends Exception {
    public InvalidRollNumberException(String message) {
        super(message);
    }
}

// Student class definition
class Student {
    private String name;
    private int rollNumber;
    private int age;
    private double marks;

    public Student(String name, int rollNumber, int age, double marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.age = age;
        this.marks = marks;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }
}


class StudentDatabase {
    private ArrayList<Student> students = new ArrayList<>();

    // Add students to the database
    public void addStudent(Student student) throws InvalidRollNumberException {
        for (Student s : students) {
            if (s.getRollNumber() == student.getRollNumber()) {
                throw new InvalidRollNumberException("Roll number already exists.");
            }
        }
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // View all students in the database
    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the database.");
            return;
        }
        System.out.println("List of Students:");
        for (Student student : students) {
            System.out.println("Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() +
                    ", Age: " + student.getAge() + ", Marks: " + student.getMarks());
        }
    }

    // Search for a student by roll number
    public void searchStudent(int rollNumber) {
        boolean found = false;
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                found = true;
                System.out.println("Student found:");
                System.out.println("Name: " + student.getName() + ", Roll Number: " + student.getRollNumber() +
                        ", Age: " + student.getAge() + ", Marks: " + student.getMarks());
                break;
            }
        }
        if (!found) {
            System.out.println("Student with roll number " + rollNumber + " not found.");
        }
    }

    // Calculate average marks of all students in the database
    public void calculateAverageMarks() {
        if (students.isEmpty()) {
            System.out.println("No students in the database.");
            return;
        }
        double totalMarks = 0;
        for (Student student : students) {
            totalMarks += student.getMarks();
        }
        double averageMarks = totalMarks / students.size();
        System.out.println("Average marks of all students: " + averageMarks);
    }
}

// Main class
public class St{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentDatabase studentDB = new StudentDatabase();

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Calculate Average Marks");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        System.out.println("Enter student name:");
                        String name = scanner.nextLine();

                        System.out.println("Enter roll number:");
                        int rollNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Enter age:");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        System.out.println("Enter marks:");
                        double marks = scanner.nextDouble();
                        scanner.nextLine(); // Consume newline

                        if (marks < 0) {
                            throw new NegativeMarksException("Marks cannot be negative.");
                        }

                        Student newStudent = new Student(name, rollNumber, age, marks);
                        studentDB.addStudent(newStudent);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid value.");
                        scanner.nextLine(); // Clear the invalid input
                    } catch (NegativeMarksException e) {
                        System.out.println(e.getMessage());
                    } catch (InvalidRollNumberException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    studentDB.viewStudents();
                    break;
                case 3:
                    try {
                        System.out.println("Enter roll number to search:");
                        int searchRollNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        studentDB.searchStudent(searchRollNumber);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid roll number.");
                        scanner.nextLine(); // Clear the invalid input
                    }
                    break;
                case 4:
                    studentDB.calculateAverageMarks();
                    break;
                case 5:
                    scanner.close();
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
