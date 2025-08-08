import javax.swing.*;

public class MainMenuView {
    public MainMenuView(Control control) {
        JFrame frame = new JFrame("IIT Attendance Tracker - Main Menu");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton signUpBtn = new JButton("Sign Up");
        signUpBtn.setBounds(140, 50, 120, 30);
        frame.add(signUpBtn);

        JButton loginBtn = new JButton("Log In");
        loginBtn.setBounds(140, 100, 120, 30);
        frame.add(loginBtn);

        JButton reportBtn = new JButton("View Reports");
        reportBtn.setBounds(140, 150, 120, 30);
        frame.add(reportBtn);

        signUpBtn.addActionListener(e -> {
            frame.dispose();
            control.showSignUpView();
        });

        loginBtn.addActionListener(e -> {
            frame.dispose();
            control.showLogInView();
        });

        reportBtn.addActionListener(e -> {
            frame.dispose();
            new ReportView(control);
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
