import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

public class ReportView {
    public ReportView(Control control) {
        JFrame frame = new JFrame("Attendance Reports");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, 600, 450);
        frame.add(bgPanel);

        JLabel title = new JLabel("Attendance Reports", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 22));
        title.setBounds(0, 10, 600, 30);
        bgPanel.add(title);

        // Batch
        JLabel batchLabel = new JLabel("Select Batch:");
        batchLabel.setBounds(30, 60, 100, 25);
        bgPanel.add(batchLabel);

        JComboBox<String> batchCombo = new JComboBox<>(CourseData.getAllBatches().toArray(new String[0]));
        batchCombo.setBounds(140, 60, 150, 25);
        bgPanel.add(batchCombo);

        // Roll
        JLabel rollLabel = new JLabel("Select Roll:");
        rollLabel.setBounds(30, 100, 100, 25);
        bgPanel.add(rollLabel);

        JComboBox<String> rollCombo = new JComboBox<>();
        rollCombo.setBounds(140, 100, 150, 25);
        bgPanel.add(rollCombo);

        // Report area
        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBounds(30, 150, 520, 220);
        bgPanel.add(scrollPane);

        // Buttons
        JButton backButton = new JButton("Back");
        backButton.setBounds(30, 380, 100, 35);
        bgPanel.add(backButton);

        // --- Populate rolls when batch changes ---
        batchCombo.addActionListener(e -> {
            rollCombo.removeAllItems();
            String selectedBatch = (String) batchCombo.getSelectedItem();
            Map<String, Student> allStudents = control.getAttendanceManager().getAllStudents();
            allStudents.values().stream()
                    .filter(s -> s.getBatch().equals(selectedBatch))
                    .sorted(Comparator.comparing(Student::getRoll))
                    .forEach(s -> rollCombo.addItem(s.getRoll()));
        });

        // --- Show report for selected student ---
        rollCombo.addActionListener(e -> {
            String selectedRoll = (String) rollCombo.getSelectedItem();
            if(selectedRoll == null) return;

            Student student = control.getAttendanceManager().getStudent(selectedRoll);
            if(student == null) return;

            StringBuilder sb = new StringBuilder();
            sb.append("Name: ").append(student.getName())
                    .append(", Roll: ").append(student.getRoll())
                    .append(", Batch: ").append(student.getBatch())
                    .append("\n\nCourses Attendance:\n");

            Map<String, AttendanceRecord> attendanceMap = student.getAttendanceMap();
            List<Map.Entry<String, AttendanceRecord>> sortedCourses = new ArrayList<>(attendanceMap.entrySet());
            sortedCourses.sort(Comparator.comparingInt(e1 -> extractSemester(e1.getKey())));

            for(Map.Entry<String, AttendanceRecord> entry : sortedCourses){
                AttendanceRecord record = entry.getValue();
                sb.append("  ").append(entry.getKey())
                        .append(": Present ").append(record.getPresent())
                        .append(" / Total ").append(record.getTotalClass())
                        .append(" (").append(String.format("%.2f%%", record.getPercentage())).append(")\n");
            }

            reportArea.setText(sb.toString());
        });

        // Back button
        backButton.addActionListener(e -> {
            frame.dispose();
            control.showMainMenu();
        });

        // Apply dark/light theme
        applyTheme(control, bgPanel, title, batchLabel, rollLabel, batchCombo, rollCombo, reportArea, backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void applyTheme(Control control, JPanel panel, JLabel title, JLabel batchLabel, JLabel rollLabel,
                            JComboBox<String> batchCombo, JComboBox<String> rollCombo,
                            JTextArea reportArea, JButton backBtn) {
        if(control.isDarkMode()){
            panel.setBackground(Color.DARK_GRAY);
            title.setForeground(Color.WHITE);
            batchLabel.setForeground(Color.WHITE);
            rollLabel.setForeground(Color.WHITE);
            batchCombo.setBackground(Color.GRAY);
            batchCombo.setForeground(Color.WHITE);
            rollCombo.setBackground(Color.GRAY);
            rollCombo.setForeground(Color.WHITE);
            reportArea.setBackground(Color.BLACK);
            reportArea.setForeground(Color.WHITE);
            backBtn.setBackground(Color.GRAY);
            backBtn.setForeground(Color.WHITE);
        } else {
            panel.setBackground(Color.WHITE);
            title.setForeground(Color.BLACK);
            batchLabel.setForeground(Color.BLACK);
            rollLabel.setForeground(Color.BLACK);
            batchCombo.setBackground(Color.WHITE);
            batchCombo.setForeground(Color.BLACK);
            rollCombo.setBackground(Color.WHITE);
            rollCombo.setForeground(Color.BLACK);
            reportArea.setBackground(Color.WHITE);
            reportArea.setForeground(Color.BLACK);
            backBtn.setBackground(Color.LIGHT_GRAY);
            backBtn.setForeground(Color.BLACK);
        }
    }

    // Extract semester from course code (e.g., "CSE 411" -> 4)
    private int extractSemester(String courseCode){
        try {
            String[] parts = courseCode.split(" ");
            if(parts.length > 1){
                int number = Integer.parseInt(parts[1]); // e.g., 411
                return number / 100; // hundreds digit = semester
            }
        } catch(Exception ignored){}
        return 0;
    }
}
