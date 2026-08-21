import java.io.Serializable;

/**
 * Student class - POJO (Plain Old Java Object) for storing student information
 */
public class Student implements Serializable {
    // Student attributes
    private int id;                  // Unique Student ID
    private String firstName;        // First Name
    private String lastName;         // Last Name
    private int age;                 // Age of Student
    private String gender;           // Male/Female/Other
    private String course;           // e.g. BCA, BCS, etc.
    private String year;             // e.g. FY, SY, TY
    private String email;            // Email ID
    private String phoneNumber;      // Contact number
    private double percentage;       // Final Percentage
    private String address;          // Home address

    // Default constructor
    public Student() {
    }

    // Parameterized constructor
    public Student(int id, String firstName, String lastName, int age, String gender, String course,
                  String year, String email, String phoneNumber, double percentage, String address) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.course = course;
        this.year = year;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.percentage = percentage;
        this.address = address;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // toString method for displaying student information
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", course='" + course + '\'' +
                ", year='" + year + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", percentage=" + percentage +
                ", address='" + address + '\'' +
                '}';
    }
}