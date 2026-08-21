import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.Arrays;

/**
 * StudentDatabase class - Handles data storage using arrays and Excel
 */
public class StudentDatabase {
    private Student[] students;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;
    private static final String EXCEL_FILE = "students.xlsx";
    
    // Constructor
    public StudentDatabase() {
        this.students = new Student[DEFAULT_CAPACITY];
        this.size = 0;
        loadFromExcel();
    }
    
    // Get all students
    public Student[] getAllStudents() {
        Student[] result = new Student[size];
        System.arraycopy(students, 0, result, 0, size);
        return result;
    }
    
    // Add a new student
    public void addStudent(Student student) {
        if (size == students.length) {
            expandCapacity();
        }
        students[size++] = student;
        saveToExcel();
    }
    
    // Expand array capacity
    private void expandCapacity() {
        int newCapacity = students.length * 2;
        students = Arrays.copyOf(students, newCapacity);
    }
    
    // Get student by ID
    public Student getStudentById(int id) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }
    
    // Update student
    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == updatedStudent.getId()) {
                students[i] = updatedStudent;
                saveToExcel();
                return true;
            }
        }
        return false;
    }
    
    // Delete student
    public boolean deleteStudent(int id) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                // Shift elements to remove the student
                System.arraycopy(students, i + 1, students, i, size - i - 1);
                students[--size] = null; // Clear the last element
                saveToExcel();
                return true;
            }
        }
        return false;
    }
    
    // Search students by name (first name or last name)
    public Student[] searchStudentsByName(String name) {
        int count = 0;
        String searchName = name.toLowerCase();
        
        // Count matching students
        for (int i = 0; i < size; i++) {
            if (students[i].getFirstName().toLowerCase().contains(searchName) || 
                students[i].getLastName().toLowerCase().contains(searchName)) {
                count++;
            }
        }
        
        // Create result array
        Student[] result = new Student[count];
        int index = 0;
        
        // Fill result array
        for (int i = 0; i < size; i++) {
            if (students[i].getFirstName().toLowerCase().contains(searchName) || 
                students[i].getLastName().toLowerCase().contains(searchName)) {
                result[index++] = students[i];
            }
        }
        
        return result;
    }
    
    // Search students by course
    public Student[] searchStudentsByCourse(String course) {
        int count = 0;
        String searchCourse = course.toLowerCase();
        
        // Count matching students
        for (int i = 0; i < size; i++) {
            if (students[i].getCourse().toLowerCase().contains(searchCourse)) {
                count++;
            }
        }
        
        // Create result array
        Student[] result = new Student[count];
        int index = 0;
        
        // Fill result array
        for (int i = 0; i < size; i++) {
            if (students[i].getCourse().toLowerCase().contains(searchCourse)) {
                result[index++] = students[i];
            }
        }
        
        return result;
    }
    
    // Get size
    public int getSize() {
        return size;
    }
    
    // Save student data to Excel file
    public void saveToExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Students");
            
            // Create header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("First Name");
            headerRow.createCell(2).setCellValue("Last Name");
            headerRow.createCell(3).setCellValue("Age");
            headerRow.createCell(4).setCellValue("Gender");
            headerRow.createCell(5).setCellValue("Course");
            headerRow.createCell(6).setCellValue("Year");
            headerRow.createCell(7).setCellValue("Email");
            headerRow.createCell(8).setCellValue("Phone Number");
            headerRow.createCell(9).setCellValue("Percentage");
            headerRow.createCell(10).setCellValue("Address");
            
            // Create data rows
            for (int i = 0; i < size; i++) {
                Row row = sheet.createRow(i + 1);
                Student student = students[i];
                
                row.createCell(0).setCellValue(student.getId());
                row.createCell(1).setCellValue(student.getFirstName());
                row.createCell(2).setCellValue(student.getLastName());
                row.createCell(3).setCellValue(student.getAge());
                row.createCell(4).setCellValue(student.getGender());
                row.createCell(5).setCellValue(student.getCourse());
                row.createCell(6).setCellValue(student.getYear());
                row.createCell(7).setCellValue(student.getEmail());
                row.createCell(8).setCellValue(student.getPhoneNumber());
                row.createCell(9).setCellValue(student.getPercentage());
                row.createCell(10).setCellValue(student.getAddress());
            }
            
            // Auto-size columns
            for (int i = 0; i < 11; i++) {
                sheet.autoSizeColumn(i);
            }
            
            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(EXCEL_FILE)) {
                workbook.write(fileOut);
            }
            
            System.out.println("Student data saved to Excel file successfully!");
        } catch (IOException e) {
            System.out.println("Error saving student data to Excel: " + e.getMessage());
        }
    }
    
    // Load student data from Excel file
    public void loadFromExcel() {
        File file = new File(EXCEL_FILE);
        if (!file.exists()) {
            System.out.println("No saved Excel data found. Starting with empty database.");
            return;
        }
        
        try (Workbook workbook = WorkbookFactory.create(file)) {
            Sheet sheet = workbook.getSheetAt(0);
            int rowCount = sheet.getPhysicalNumberOfRows();
            
            // Skip header row
            if (rowCount <= 1) {
                return;
            }
            
            // Ensure capacity
            if (rowCount - 1 > students.length) {
                students = new Student[rowCount - 1];
            }
            
            // Read data rows
            size = 0;
            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                int id = (int) row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String firstName = row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String lastName = row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                int age = (int) row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String gender = row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String course = row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String year = row.getCell(6, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String email = row.getCell(7, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                String phoneNumber = row.getCell(8, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                double percentage = row.getCell(9, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getNumericCellValue();
                String address = row.getCell(10, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue();
                
                Student student = new Student(id, firstName, lastName, age, gender, course, 
                                            year, email, phoneNumber, percentage, address);
                students[size++] = student;
            }
            
            System.out.println("Student data loaded from Excel file successfully!");
        } catch (IOException e) {
            System.out.println("Error loading student data from Excel: " + e.getMessage());
        }
    }
}