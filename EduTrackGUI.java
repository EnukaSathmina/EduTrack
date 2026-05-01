// EduTrackGUI.java
// This class provides a GUI version of the EduTrack Student Management System using Java Swing.
// It includes buttons for all operations and displays student data in a table for better visualization.

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class EduTrackGUI extends JFrame {
    private ArrayList<Student> students = new ArrayList<>();
    private JTable studentTable;
    private DefaultTableModel tableModel;
    private JButton addButton, viewButton, searchButton, deleteButton, exitButton;
    private static final String FILE_NAME = "students.txt";
    private static final String[] SUBJECTS = {"Math", "English", "Science"};

    public EduTrackGUI() {
        // Load students from file at startup
        loadFromFile();

        // Set up the GUI
        setTitle("EduTrack Student Management System");
        setSize(800, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());

        // Set background color
        getContentPane().setBackground(new Color(240, 248, 255)); // Alice blue

        // Title label
        JLabel titleLabel = new JLabel("EduTrack - Student Management System", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);

        // Table for displaying students
        String[] columnNames = {"ID", "Name", "Math", "English", "Science", "Average", "Grade"};
        tableModel = new DefaultTableModel(columnNames, 0);
        studentTable = new JTable(tableModel);
        studentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        studentTable.setRowHeight(25);
        studentTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        studentTable.setFont(new Font("Arial", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        addButton = createStyledButton("Add Student", new Color(34, 139, 34));
        viewButton = createStyledButton("View All", new Color(70, 130, 180));
        searchButton = createStyledButton("Search", new Color(255, 165, 0));
        deleteButton = createStyledButton("Delete", new Color(220, 20, 60));
        exitButton = createStyledButton("Exit", new Color(128, 128, 128));

        buttonPanel.add(addButton);
        buttonPanel.add(viewButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners
        addButton.addActionListener(new AddStudentListener());
        viewButton.addActionListener(new ViewStudentsListener());
        searchButton.addActionListener(new SearchStudentListener());
        deleteButton.addActionListener(new DeleteStudentListener());
        exitButton.addActionListener(new ExitListener());

        // Initial view
        refreshTable();

        setVisible(true);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        button.setPreferredSize(new Dimension(130, 30));
        button.setToolTipText(text);
        return button;
    }

    // Method to refresh the table with current student data
    private void refreshTable() {
        tableModel.setRowCount(0); // Clear table
        for (Student student : students) {
            double[] marks = student.getMarks();
            Object[] row = {
                student.getId(),
                student.getName(),
                marks[0],
                marks[1],
                marks[2],
                String.format("%.2f", student.getAverage()),
                student.getGrade()
            };
            tableModel.addRow(row);
        }
    }

    // Custom dialog for adding a student
    private class AddStudentDialog extends JDialog {
        private JTextField idField, nameField;
        private JTextField[] markFields;
        private JButton okButton, cancelButton;
        private boolean confirmed = false;

        public AddStudentDialog(Frame parent) {
            super(parent, "Add Student", true);
            setSize(400, 280);
            setLayout(new GridBagLayout());
            setLocationRelativeTo(parent);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);

            // ID
            gbc.gridx = 0; gbc.gridy = 0;
            add(new JLabel("ID:"), gbc);
            gbc.gridx = 1;
            idField = new JTextField(12);
            add(idField, gbc);

            // Name
            gbc.gridx = 0; gbc.gridy = 1;
            add(new JLabel("Name:"), gbc);
            gbc.gridx = 1;
            nameField = new JTextField(12);
            add(nameField, gbc);

            // Marks
            markFields = new JTextField[SUBJECTS.length];
            for (int i = 0; i < SUBJECTS.length; i++) {
                gbc.gridx = 0; gbc.gridy = i + 2;
                add(new JLabel(SUBJECTS[i] + " Marks:"), gbc);
                gbc.gridx = 1;
                markFields[i] = new JTextField(12);
                add(markFields[i], gbc);
            }

            // Buttons
            JPanel buttonPanel = new JPanel();
            okButton = new JButton("OK");
            cancelButton = new JButton("Cancel");
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);

            gbc.gridx = 0; gbc.gridy = SUBJECTS.length + 2; gbc.gridwidth = 2;
            add(buttonPanel, gbc);

            okButton.addActionListener(e -> {
                confirmed = true;
                setVisible(false);
            });
            cancelButton.addActionListener(e -> {
                confirmed = false;
                setVisible(false);
            });
        }

        public boolean isConfirmed() {
            return confirmed;
        }

        public String getId() {
            return idField.getText().trim();
        }

        public String getName() {
            return nameField.getText().trim();
        }

        public String[] getMarks() {
            String[] marks = new String[markFields.length];
            for (int i = 0; i < markFields.length; i++) {
                marks[i] = markFields[i].getText().trim();
            }
            return marks;
        }
    }

    // ActionListener for Add Student
    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            AddStudentDialog dialog = new AddStudentDialog(EduTrackGUI.this);
            dialog.setVisible(true);

            if (dialog.isConfirmed()) {
                try {
                    String idStr = dialog.getId();
                    if (idStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "ID cannot be empty.");
                        return;
                    }
                    int id = Integer.parseInt(idStr);

                    for (Student s : students) {
                        if (s.getId() == id) {
                            JOptionPane.showMessageDialog(null, "Student with this ID already exists.");
                            return;
                        }
                    }

                    String name = dialog.getName();
                    if (name.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Name cannot be empty.");
                        return;
                    }

                    String[] markStrs = dialog.getMarks();
                    double mark1 = Double.parseDouble(markStrs[0]);
                    double mark2 = Double.parseDouble(markStrs[1]);
                    double mark3 = Double.parseDouble(markStrs[2]);

                    if (mark1 < 0 || mark1 > 100 || mark2 < 0 || mark2 > 100 || mark3 < 0 || mark3 > 100) {
                        JOptionPane.showMessageDialog(null, "Marks must be between 0 and 100.");
                        return;
                    }

                    Student student = new Student(id, name, mark1, mark2, mark3);
                    students.add(student);
                    JOptionPane.showMessageDialog(null, "Student added successfully!");
                    refreshTable();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid numbers.");
                }
            }
        }
    }

    // ActionListener for View All Students
    private class ViewStudentsListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshTable();
            if (students.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No students found.");
            }
        }
    }

    // ActionListener for Search Student
    private class SearchStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String idStr = JOptionPane.showInputDialog("Enter student ID to search:");
                if (idStr == null) return;
                int id = Integer.parseInt(idStr);

                for (int i = 0; i < students.size(); i++) {
                    if (students.get(i).getId() == id) {
                        studentTable.setRowSelectionInterval(i, i);
                        studentTable.scrollRectToVisible(studentTable.getCellRect(i, 0, true));
                        JOptionPane.showMessageDialog(null, "Student found and selected in the table.");
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Student with ID " + id + " not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID.");
            }
        }
    }

    // ActionListener for Delete Student
    private class DeleteStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String idStr = JOptionPane.showInputDialog("Enter student ID to delete:");
                if (idStr == null) return;
                int id = Integer.parseInt(idStr);

                for (Student student : students) {
                    if (student.getId() == id) {
                        students.remove(student);
                        JOptionPane.showMessageDialog(null, "Student deleted successfully!");
                        refreshTable();
                        return;
                    }
                }
                JOptionPane.showMessageDialog(null, "Student with ID " + id + " not found.");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid ID.");
            }
        }
    }

    // ActionListener for Exit
    private class ExitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveToFile();
            JOptionPane.showMessageDialog(null, "Exiting EduTrack. Goodbye!");
            System.exit(0);
        }
    }

    // Method to save students to file
    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.println(student.getId() + "," + student.getName() + "," +
                               student.getMarks()[0] + "," + student.getMarks()[1] + "," + student.getMarks()[2]);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error saving to file: " + e.getMessage());
        }
    }

    // Method to load students from file
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    double mark1 = Double.parseDouble(parts[2]);
                    double mark2 = Double.parseDouble(parts[3]);
                    double mark3 = Double.parseDouble(parts[4]);
                    students.add(new Student(id, name, mark1, mark2, mark3));
                }
            }
        } catch (FileNotFoundException e) {
            // No file, start fresh
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error loading from file: " + e.getMessage());
        }
    }
}
