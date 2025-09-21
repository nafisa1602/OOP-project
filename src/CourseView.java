import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CourseView {
    public CourseView(Control control, Student student) {
        JFrame frame = new JFrame("Courses - " + student.getRoll());
        frame.setSize(550, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, 550, 450);
        frame.add(bgPanel);

        JLabel title = new JLabel("Student: " + student.getName() + " (" + student.getRoll() + ")", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 20));
        title.setBounds(50, 10, 450, 30);
        bgPanel.add(title);

        JLabel semLabel = new JLabel("Select Semester:");
        semLabel.setBounds(50, 60, 120, 25);
        bgPanel.add(semLabel);

        JComboBox<String> semCombo = new JComboBox<>(new String[]{"1","2","3","4","5","6","7","8"});
        semCombo.setBounds(180, 60, 120, 25);
        bgPanel.add(semCombo);

        JLabel courseLabel = new JLabel("Select Course:");
        courseLabel.setBounds(50, 100, 120, 25);
        bgPanel.add(courseLabel);

        JComboBox<String> courseCombo = new JComboBox<>();
        courseCombo.setBounds(180, 100, 200, 25);
        bgPanel.add(courseCombo);

        JButton presentBtn = createButton("Mark Present", new Color(34,139,34));
        presentBtn.setBounds(100, 160, 130, 35);
        bgPanel.add(presentBtn);

        JButton absentBtn = createButton("Mark Absent", new Color(178,34,34));
        absentBtn.setBounds(270, 160, 130, 35);
        bgPanel.add(absentBtn);

        JButton backBtn = createButton("Back", Color.GRAY);
        backBtn.setBounds(50, 350, 100, 35);
        bgPanel.add(backBtn);

        // --- Update courses when semester changes ---
        semCombo.addActionListener(e -> {
            String semester = (String) semCombo.getSelectedItem();
            List<String> courses = CourseData.getCoursesForBatchAndSemester(student.getBatch(), semester);
            courseCombo.removeAllItems();
            if(courses != null){
                for(String c: courses) courseCombo.addItem(c);
            }
        });

        // --- Course selection listener ---
        courseCombo.addActionListener(e -> {
            String course = (String) courseCombo.getSelectedItem();
            if(course != null){
                updateButtonState(course, student, presentBtn, absentBtn);
            }
        });

        presentBtn.addActionListener(e -> {
            String course = (String) courseCombo.getSelectedItem();
            if(course != null){
                student.attendanceForCourse(course, true);
                updateButtonState(course, student, presentBtn, absentBtn);
            }
        });

        absentBtn.addActionListener(e -> {
            String course = (String) courseCombo.getSelectedItem();
            if(course != null){
                student.attendanceForCourse(course, false);
                updateButtonState(course, student, presentBtn, absentBtn);
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            control.showMainMenu();
        });

        applyTheme(control, bgPanel, title, semLabel, courseLabel, semCombo, courseCombo, presentBtn, absentBtn, backBtn);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createButton(String text, Color bg){
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        return btn;
    }

    private void updateButtonState(String course, Student student, JButton presentBtn, JButton absentBtn){
        var record = student.getAttendanceRecord(course);
        if(record.getLastMarkedDate() != null && record.getLastMarkedDate().equals(java.time.LocalDate.now())){
            presentBtn.setEnabled(false);
            absentBtn.setEnabled(false);
            JOptionPane.showMessageDialog(null,
                    "Attendance already marked today for " + course,
                    "Info",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            presentBtn.setEnabled(true);
            absentBtn.setEnabled(true);
        }
    }

    private void applyTheme(Control control, JPanel panel, JLabel title, JLabel semLabel, JLabel courseLabel,
                            JComboBox<String> semCombo, JComboBox<String> courseCombo, JButton... buttons){
        if(control.isDarkMode()){
            panel.setBackground(Color.DARK_GRAY);
            title.setForeground(Color.WHITE);
            semLabel.setForeground(Color.WHITE);
            courseLabel.setForeground(Color.WHITE);
            semCombo.setBackground(Color.GRAY);
            semCombo.setForeground(Color.WHITE);
            courseCombo.setBackground(Color.GRAY);
            courseCombo.setForeground(Color.WHITE);
            for(JButton b: buttons) b.setBackground(b.getBackground().darker());
        } else {
            panel.setBackground(Color.WHITE);
            title.setForeground(Color.BLACK);
            semLabel.setForeground(Color.BLACK);
            courseLabel.setForeground(Color.BLACK);
            semCombo.setBackground(Color.WHITE);
            semCombo.setForeground(Color.BLACK);
            courseCombo.setBackground(Color.WHITE);
            courseCombo.setForeground(Color.BLACK);
            for(JButton b: buttons) {
                if(b.getText().equals("Mark Present")) b.setBackground(new Color(34,139,34));
                else if(b.getText().equals("Mark Absent")) b.setBackground(new Color(178,34,34));
                else b.setBackground(Color.GRAY);
            }
        }
    }
}
