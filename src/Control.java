import javax.swing.*;

public class Control {
    private AttendanceManager attendanceManager;
    private boolean darkMode = false;

    public Control() {
        attendanceManager = new AttendanceManager();
    }

    public boolean isDarkMode() { return darkMode; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }

    public void showMainMenu() { new MainMenuView(this); }
    public void showSignUpView() { new SignUpView(this); }
    public void showLogInView() { new LoginView(this); }
    public void showCourseView(Student student) { new CourseView(this, student); }
    public void showReportView() { new ReportView(this); }

    public void registerStudent(String name, String roll) {
        Student student = new Student(name, roll);
        boolean add = attendanceManager.addStudent(student);
        if (!add) {
            JOptionPane.showMessageDialog(null,
                    "Roll number already registered!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            showSignUpView();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            showLogInView();
        }
    }

    public void loginStudent(String roll) {
        Student student = attendanceManager.getStudent(roll);
        if (student == null) {
            JOptionPane.showMessageDialog(null,
                    "Roll number not found. Please sign up first.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            showLogInView();
        } else {
            showCourseView(student);
        }
    }

    public void markAttendance(Student student, String course, boolean present) {
        student.attendanceForCourse(course, present);
        attendanceManager.updateStudent(student);
    }

    public AttendanceManager getAttendanceManager() { return attendanceManager; }
}
