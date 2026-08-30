# 🎓 Student Management System (Java & JDBC)

> **Full-featured Java Swing desktop application for managing student academic records, course enrollments, GPA calculations, and JDBC database persistence.**

---

## ✨ Features

- 🖥️ **Java Swing Desktop GUI** (`StudentManagementSystem.java`)
  - Intuitive graphical user interface with student tables, search filters, and form dialogs.

- 🗄️ **JDBC Database Integration** (`StudentDatabase.java`)
  - Full CRUD operations connected to MySQL / SQLite via JDBC driver (`lib/`).

- 📊 **GPA & Academic Analytics** (`StudentService.java`)
  - Automatic GPA computation, grade performance tracking, and course enrollment management.

---

## 🛠️ Tech Stack

- **Language**: Java 17+ (Core Java)
- **GUI Framework**: Java Swing & AWT
- **Database**: JDBC, MySQL / SQLite
- **Build Tool**: Maven (`pom.xml`)

---

## 🚀 Getting Started

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Omkar4812x/Student-Management-System-Java.git
   cd Student-Management-System-Java
   ```

2. **Compile and Run**:
   - On Windows:
     ```cmd
     compile_and_run.bat
     ```
   - Or using Maven:
     ```bash
     mvn clean package
     java -jar target/StudentManagementSystem.jar
     ```

---

## 📄 License

Distributed under the MIT License.
