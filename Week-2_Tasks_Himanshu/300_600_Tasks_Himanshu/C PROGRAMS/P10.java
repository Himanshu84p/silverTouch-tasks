import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Student {
    private String studentId;
    private String name;
    private int age;

    public Student(String studentId, String name, int age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Student ID: " + studentId + "\nName: " + name + "\nAge: " + age;
    }
}

class StudentInformationSystem {
    private List<Student> students;

    public StudentInformationSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(String studentId, String name, int age) {
        students.add(new Student(studentId, name, age));
        System.out.println("Student added successfully.");
    }

    public void displayAllStudents() {
        System.out.println("\nAll Students:");
        for (Student student : students) {
            System.out.println(student);
            System.out.println("-----------");
        }
    }

    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}

public class P10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentInformationSystem studentSystem = new StudentInformationSystem();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Find Student by ID");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter student age: ");
                    int age = scanner.nextInt();
                    studentSystem.addStudent(studentId, name, age);
                    break;
                case 2:
                    studentSystem.displayAllStudents();
                    break;
                case 3:
                    System.out.print("Enter student ID to find: ");
                    String searchId = scanner.nextLine();
                    Student foundStudent = studentSystem.findStudentById(searchId);
                    if (foundStudent != null) {
                        System.out.println("\nStudent found:\n" + foundStudent);
                    } else {
                        System.out.println("\nStudent not found.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
