import javax.swing.*;

public class CourseView {
    private static final String[] COURSES = {"select", "1203", "1206", "1211", "1212", "1215", "1216", "1206L", "1211L", "1215L"};

    public CourseView(Control control, Student student) {
        JFrame frame = new JFrame("Courses - " + student.getRoll());
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel title = new JLabel("Student: " + student.getName() + " (" + student.getRoll() + ")");
        title.setBounds(20, 10, 300, 25);
        frame.add(title);

        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setBounds(20, 60, 100, 25);
        frame.add(courseLabel);

        JComboBox<String> courseCombo = new JComboBox<>(COURSES);
        courseCombo.setBounds(130, 60, 150, 25);
        frame.add(courseCombo);

        JButton presentBtn = new JButton("Mark Present");
        presentBtn.setBounds(100, 120, 120, 30);
        frame.add(presentBtn);

        JButton absentBtn = new JButton("Mark Absent");
        absentBtn.setBounds(250, 120, 120, 30);
        frame.add(absentBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(20, 260, 80, 30);
        frame.add(backBtn);

        presentBtn.addActionListener(e -> {
            String course = (String) courseCombo.getSelectedItem();
            if (course == null || course.equals("select")) {
                JOptionPane.showMessageDialog(frame, "Please select a valid course.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            control.markAttendance(student, course, true);
            JOptionPane.showMessageDialog(frame, "Attendance marked as Present for course " + course);
        });

        absentBtn.addActionListener(e -> {
            String course = (String) courseCombo.getSelectedItem();
            if (course == null || course.equals("select")) {
                JOptionPane.showMessageDialog(frame, "Please select a valid course.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            control.markAttendance(student, course, false);
            JOptionPane.showMessageDialog(frame, "Attendance marked as Absent for course " + course);
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            control.showMainMenu();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
