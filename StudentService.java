/**
 * StudentService class - Contains business logic for student management
 */
public class StudentService {
    private StudentDatabase database;
    
    // Constructor
    public StudentService() {
        this.database = new StudentDatabase();
    }
    
    // Add a new student
    public void addStudent(Student student) {
        database.addStudent(student);
        System.out.println("Student added successfully!");
    }
    
    // Update student details
    public void updateStudent(int id, Student updatedStudent) {
        if (database.updateStudent(updatedStudent)) {
            System.out.println("Student updated successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }
    
    // Delete student
    public void deleteStudent(int id) {
        if (database.deleteStudent(id)) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student with ID " + id + " not found!");
        }
    }
    
    // Search student by ID
    public Student searchStudentById(int id) {
        Student student = database.getStudentById(id);
        if (student == null) {
            System.out.println("Student with ID " + id + " not found!");
        }
        return student;
    }
    
    // Search students by name
    public Student[] searchStudentsByName(String name) {
        Student[] students = database.searchStudentsByName(name);
        if (students.length == 0) {
            System.out.println("No students found with name containing '" + name + "'!");
        }
        return students;
    }
    
    // View all students
    public Student[] viewAllStudents() {
        Student[] students = database.getAllStudents();
        if (students.length == 0) {
            System.out.println("No students found in the database!");
        }
        return students;
    }
    
    // Filter students by course
    public Student[] filterByCourse(String course) {
        Student[] students = database.searchStudentsByCourse(course);
        if (students.length == 0) {
            System.out.println("No students found in course '" + course + "'!");
        }
        return students;
    }
    
    // Filter students by year
    public Student[] filterByYear(String year) {
        Student[] allStudents = database.getAllStudents();
        int count = 0;
        String searchYear = year.toLowerCase();
        
        // Count matching students first
        for (Student student : allStudents) {
            if (student != null && student.getYear().toLowerCase().equals(searchYear)) {
                count++;
            }
        }
        
        // Create result array with exact size
        Student[] result = new Student[count];
        int index = 0;
        
        // Fill result array
        for (Student student : allStudents) {
            if (student != null && student.getYear().toLowerCase().equals(searchYear)) {
                result[index++] = student;
            }
        }
        
        if (result.length == 0) {
            System.out.println("No students found in year '" + year + "'!");
        }
        return result;
    }
    
    // Display student with highest percentage (topper)
    public Student displayTopper() {
        Student[] students = database.getAllStudents();
        if (students.length == 0) {
            System.out.println("No students found in the database!");
            return null;
        }
        
        Student topper = null;
        double highestPercentage = -1;
        
        for (Student student : students) {
            if (student != null && student.getPercentage() > highestPercentage) {
                highestPercentage = student.getPercentage();
                topper = student;
            }
        }
        
        return topper;
    }
    
    // Sort students by name
    public Student[] sortStudentsByName() {
        Student[] students = database.getAllStudents();
        if (students.length == 0) {
            System.out.println("No students found in the database!");
            return students;
        }
        
        // Bubble sort by first name and then last name
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j] != null && students[j+1] != null) {
                    // Compare first names
                    int firstNameComparison = students[j].getFirstName().compareTo(students[j+1].getFirstName());
                    
                    // If first names are the same, compare last names
                    if (firstNameComparison == 0) {
                        if (students[j].getLastName().compareTo(students[j+1].getLastName()) > 0) {
                            // Swap
                            Student temp = students[j];
                            students[j] = students[j+1];
                            students[j+1] = temp;
                        }
                    } 
                    // If first names are different and first name of j > first name of j+1
                    else if (firstNameComparison > 0) {
                        // Swap
                        Student temp = students[j];
                        students[j] = students[j+1];
                        students[j+1] = temp;
                    }
                }
            }
        }
        
        return students;
    }
    
    // Sort students by percentage
    public Student[] sortStudentsByPercentage() {
        Student[] students = database.getAllStudents();
        if (students.length == 0) {
            System.out.println("No students found in the database!");
            return students;
        }
        
        // Bubble sort by percentage (descending order)
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j] != null && students[j+1] != null && 
                    students[j].getPercentage() < students[j+1].getPercentage()) {
                    // Swap
                    Student temp = students[j];
                    students[j] = students[j+1];
                    students[j+1] = temp;
                }
            }
        }
        
        return students;
    }
    
    // Generate report card for a student
    public String generateReportCard(int id) {
        Student student = database.getStudentById(id);
        if (student == null) {
            return "Student with ID " + id + " not found!";
        }
        
        StringBuilder report = new StringBuilder();
        report.append("\n===== STUDENT REPORT CARD =====\n");
        report.append("ID: ").append(student.getId()).append("\n");
        report.append("Name: ").append(student.getFirstName()).append(" ").append(student.getLastName()).append("\n");
        report.append("Age: ").append(student.getAge()).append("\n");
        report.append("Gender: ").append(student.getGender()).append("\n");
        report.append("Course: ").append(student.getCourse()).append("\n");
        report.append("Year: ").append(student.getYear()).append("\n");
        report.append("Email: ").append(student.getEmail()).append("\n");
        report.append("Phone: ").append(student.getPhoneNumber()).append("\n");
        report.append("Address: ").append(student.getAddress()).append("\n");
        report.append("Percentage: ").append(String.format("%.2f%%", student.getPercentage())).append("\n");
        report.append("Grade: ").append(calculateGrade(student.getPercentage())).append("\n");
        report.append("============================\n");
        
        return report.toString();
    }
    
    // Calculate grade based on percentage
    private String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B+";
        } else if (percentage >= 60) {
            return "B";
        } else if (percentage >= 50) {
            return "C";
        } else if (percentage >= 40) {
            return "D";
        } else {
            return "F";
        }
    }
    
    // Save student data to Excel
    public void saveToExcel() {
        database.saveToExcel();
    }
}