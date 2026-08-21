import java.util.Scanner;

/**
 * StudentManagementSystem class - Main class with menu-driven interface
 */
public class StudentManagementSystem {
    private static StudentService service;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        service = new StudentService();
        scanner = new Scanner(System.in);
        
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    searchStudent();
                    break;
                case 5:
                    viewAllStudents();
                    break;
                case 6:
                    filterByCourse();
                    break;
                case 7:
                    filterByYear();
                    break;
                case 8:
                    displayTopper();
                    break;
                case 9:
                    sortStudents();
                    break;
                case 10:
                    generateReportCard();
                    break;
                case 11:
                    service.saveToExcel();
                    System.out.println("Data saved to Excel successfully!");
                    break;
                case 0:
                    service.saveToExcel();
                    exit = true;
                    System.out.println("Data saved to Excel. Thank you for using Student Management System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Search Student");
        System.out.println("5. View All Students");
        System.out.println("6. Filter By Course");
        System.out.println("7. Filter By Year");
        System.out.println("8. Display Topper");
        System.out.println("9. Sort Students");
        System.out.println("10. Generate Report Card");
        System.out.println("11. Save To Excel");
        System.out.println("0. Exit");
    }
    
    private static void addStudent() {
        System.out.println("\n----- Add New Student -----");
        
        int id = getIntInput("Enter Student ID: ");
        String firstName = getStringInput("Enter First Name: ");
        String lastName = getStringInput("Enter Last Name: ");
        int age = getIntInput("Enter Age: ");
        String gender = getStringInput("Enter Gender (Male/Female/Other): ");
        String course = getStringInput("Enter Course: ");
        String year = getStringInput("Enter Year: ");
        String email = getStringInput("Enter Email: ");
        String phoneNumber = getStringInput("Enter Phone Number: ");
        double percentage = getDoubleInput("Enter Percentage: ");
        String address = getStringInput("Enter Address: ");
        
        Student student = new Student(id, firstName, lastName, age, gender, course, 
                                     year, email, phoneNumber, percentage, address);
        service.addStudent(student);
    }
    
    private static void updateStudent() {
        System.out.println("\n----- Update Student -----");
        
        int id = getIntInput("Enter Student ID to update: ");
        Student student = service.searchStudentById(id);
        
        if (student != null) {
            System.out.println("Current details:");
            System.out.println(student);
            
            System.out.println("\nEnter new details:");
            String firstName = getStringInput("Enter First Name: ");
            String lastName = getStringInput("Enter Last Name: ");
            int age = getIntInput("Enter Age: ");
            String gender = getStringInput("Enter Gender (Male/Female/Other): ");
            String course = getStringInput("Enter Course: ");
            String year = getStringInput("Enter Year: ");
            String email = getStringInput("Enter Email: ");
            String phoneNumber = getStringInput("Enter Phone Number: ");
            double percentage = getDoubleInput("Enter Percentage: ");
            String address = getStringInput("Enter Address: ");
            
            Student updatedStudent = new Student(id, firstName, lastName, age, gender, course, 
                                               year, email, phoneNumber, percentage, address);
            service.updateStudent(id, updatedStudent);
        }
    }
    
    private static void deleteStudent() {
        System.out.println("\n----- Delete Student -----");
        
        int id = getIntInput("Enter Student ID to delete: ");
        service.deleteStudent(id);
    }
    
    private static void searchStudent() {
        System.out.println("\n----- Search Student -----");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        
        int choice = getIntInput("Enter your choice: ");
        
        switch (choice) {
            case 1:
                int id = getIntInput("Enter Student ID: ");
                Student student = service.searchStudentById(id);
                if (student != null) {
                    System.out.println("\nStudent found:");
                    System.out.println(student);
                }
                break;
            case 2:
                String name = getStringInput("Enter Student Name: ");
                Student[] students = service.searchStudentsByName(name);
                displayStudents(students);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void viewAllStudents() {
        System.out.println("\n----- All Students -----");
        Student[] students = service.viewAllStudents();
        displayStudents(students);
    }
    
    private static void filterByCourse() {
        System.out.println("\n----- Filter By Course -----");
        String course = getStringInput("Enter Course: ");
        Student[] students = service.filterByCourse(course);
        displayStudents(students);
    }
    
    private static void filterByYear() {
        System.out.println("\n----- Filter By Year -----");
        String year = getStringInput("Enter Year: ");
        Student[] students = service.filterByYear(year);
        displayStudents(students);
    }
    
    private static void displayTopper() {
        System.out.println("\n----- Topper Student -----");
        Student topper = service.displayTopper();
        if (topper != null) {
            System.out.println("Topper: " + topper.getFirstName() + " " + topper.getLastName() + 
                              " with " + topper.getPercentage() + "%");
            System.out.println(topper);
        }
    }
    
    private static void sortStudents() {
        System.out.println("\n----- Sort Students -----");
        System.out.println("1. Sort by Name");
        System.out.println("2. Sort by Percentage");
        
        int choice = getIntInput("Enter your choice: ");
        
        Student[] students;
        switch (choice) {
            case 1:
                students = service.sortStudentsByName();
                System.out.println("\nStudents sorted by name:");
                displayStudents(students);
                break;
            case 2:
                students = service.sortStudentsByPercentage();
                System.out.println("\nStudents sorted by percentage (highest first):");
                displayStudents(students);
                break;
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    private static void generateReportCard() {
        System.out.println("\n----- Generate Report Card -----");
        int id = getIntInput("Enter Student ID: ");
        String reportCard = service.generateReportCard(id);
        System.out.println(reportCard);
    }
    
    private static void displayStudents(Student[] students) {
        if (students.length > 0) {
            System.out.println("\nFound " + students.length + " students:");
            for (Student student : students) {
                if (student != null) {
                    System.out.println(student);
                    System.out.println("------------------------");
                }
            }
        } else {
            System.out.println("No students found.");
        }
    }
    
    private static int getIntInput(String prompt) {
        int input = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
        
        return input;
    }
    
    private static double getDoubleInput(String prompt) {
        double input = 0;
        boolean valid = false;
        
        while (!valid) {
            try {
                System.out.print(prompt);
                input = Double.parseDouble(scanner.nextLine().trim());
                valid = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
        
        return input;
    }
    
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}