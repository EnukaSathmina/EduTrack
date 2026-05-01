<h2 align="center">👨‍💻 Author</h2>

<p align="center">
  Made by <b>Enuka Sathmina</b> 🎓 EduTrack

<p align="center">
  <img src="https://img.shields.io/badge/EduTrack-v1.0.0-0066cc?style=for-the-badge&logo=java&logoColor=white" />
  <img src="https://img.shields.io/badge/Status-Maintained-success?style=for-the-badge" />
  <img src="https://img.shields.io/badge/License-MIT-white?style=for-the-badge" />
</p>

<p align="center">
  <strong>A professional Student Management System featuring a high-performance Java Swing interface.</strong><br>
  Streamline academic administration with automated grading, real-time data visualization, and persistent record-keeping[cite: 10, 11].
</p>

---

# 💎 Why EduTrack?

EduTrack provides a sophisticated alternative to console-based management tools by utilizing a rich Graphical User Interface (GUI) built for modern educational workflows[cite: 10]. It focuses on reducing administrative overhead through automation and intuitive design[cite: 10, 11].

### 🚀 Core Strengths
- **Automated Grading:** Dynamically calculates average marks and assigns letter grades based on student performance[cite: 10, 11].
- **Data Persistence:** Integrated I/O logic automatically saves and reloads student data from `students.txt`[cite: 9, 10].
- **Robust Validation:** Prevents data entry errors with built-in checks for duplicate IDs and valid mark ranges (0-100)[cite: 2, 10].
- **Professional UI:** Features a sortable `JTable`, Alice Blue themes, and styled action buttons for a premium look and feel[cite: 9, 10].

---

# 🛠️ Built With

<p align="left">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Swing-GUI_Library-blue?style=flat-square" />
  <img src="https://img.shields.io/badge/AWT-Layout_Management-lightgrey?style=flat-square" />
  <img src="https://img.shields.io/badge/Flat_File-Persistence-db7093?style=flat-square" />
</p>

---

## 📷 Preview

> ![Image Alt](https://github.com/EnukaSathmina/EduTrack/blob/main/img.png?raw=true)

# 📊 Management Capabilities

| Feature | Status | Description |
| :--- | :---: | :--- |
| **Student Enrollment** | ✅ | Dedicated dialog for registering students with custom IDs and names[cite: 1, 2]. |
| **Academic Tracking** | ✅ | Monitor marks across Math, English, and Science subjects[cite: 10]. |
| **Smart Search** | ✅ | Locate records by ID with automatic table scrolling and row highlighting[cite: 7, 10]. |
| **Record Maintenance** | ✅ | Effortlessly delete old student records with instant table synchronization[cite: 3, 10]. |
| **Data Persistence** | ✅ | Seamlessly save progress to local storage on exit[cite: 4, 10]. |

---

# 🏁 Getting Started

## 1. Clone the Repository
To get a local copy of this project up and running, use the following git command[cite: 11]:
```bash
git clone https://github.com/EnukaSathmina/EduTrack.git
```

## 2. Project Setup
Ensure your project structure includes the primary GUI controller and supporting components[cite: 1, 9]:
```text
📁 EduTrack/
   ├── EduTrackGUI.java      # Main Application UI and Logic[cite: 10]
   ├── Student.java          # Student Data Model[cite: 10]
   └── students.txt          # Local Database File[cite: 10]
```

## 3. Implementation
Compile and run the application using the JDK[cite: 11]:

### Compile the source files
```
javac EduTrackGUI.java
```

### Execute the application
```
java EduTrackGUI
```
