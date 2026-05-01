// Main.java
// This is the entry point for the EduTrack Student Management System.
// It launches the GUI version of the application.

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EduTrackGUI();
            }
        });
    }
}