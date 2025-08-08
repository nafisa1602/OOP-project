import javax.swing.*;
import java.util.Map;

public class ReportView {
    public ReportView(Control control) {
        JFrame frame = new JFrame("All Students Report");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JTextArea reportArea = new JTextArea();
        reportArea.setEditable(false);

        StringBuilder reportText = new StringBuilder();
        Map<String, Student> students = control.getAttendanceManager().getAllStudents();

        if (students.isEmpty()) {
            reportText.append("No student data available.");
        } else {
            for (Student student : students.values()) {
                reportText.append("Name: ").append(student.getName())
                        .append(", Roll: ").append(student.getRoll())
                        .append(", Batch: ").append(student.getBatch())
                        .append("\nCourses Attendance:\n");
                for (Map.Entry<String, AttendanceRecord> entry : student.getAttendanceMap().entrySet()) {
                    AttendanceRecord record = entry.getValue();
                    reportText.append("  ").append(entry.getKey())
                            .append(": Present ").append(record.getPresent())
                            .append(" / Total ").append(record.getTotalClass())
                            .append(" (").append(String.format("%.2f%%", record.getPercentage())).append(")\n");
                }
                reportText.append("\n");
            }
        }

        reportArea.setText(reportText.toString());

        JScrollPane scrollPane = new JScrollPane(reportArea);
        scrollPane.setBounds(10, 10, 560, 320);
        frame.add(scrollPane);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 340, 80, 30);
        frame.add(backButton);

        backButton.addActionListener(e -> {
            frame.dispose();
            control.showMainMenu();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
