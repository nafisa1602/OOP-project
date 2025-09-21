import javax.swing.*;
import java.awt.*;

public class MainMenuView {
    public MainMenuView(Control control) {
        JFrame frame = new JFrame("IIT Attendance Tracker - Main Menu");
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0, 0, 500, 450);
        frame.add(bgPanel);

        // --- DU Logo ---
        ImageIcon duIcon = new ImageIcon("du_logo.png");
        Image scaledDU = duIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        ImageIcon duScaledIcon = new ImageIcon(scaledDU);
        JLabel duLabel = new JLabel(duScaledIcon);
        duLabel.setBounds(20, 20, 80, 80);
        bgPanel.add(duLabel);

        // --- Main Title ---
        JLabel title = new JLabel("IIT Attendance Tracker", SwingConstants.CENTER);
        title.setFont(new Font("Verdana", Font.BOLD, 24));
        title.setBounds(0, 30, 500, 40);
        bgPanel.add(title);

        // --- Subtitle ---
        JLabel subtitle = new JLabel("Professionalism | Excellence | Respect", SwingConstants.CENTER);
        subtitle.setFont(new Font("Tahoma", Font.ITALIC, 16));
        subtitle.setBounds(0, 70, 500, 25);
        subtitle.setForeground(Color.DARK_GRAY);
        bgPanel.add(subtitle);

        // --- Buttons ---
        JButton signUpBtn = createButton("Sign Up", new Color(70, 130, 180));
        signUpBtn.setBounds(180, 120, 140, 40);
        bgPanel.add(signUpBtn);

        JButton loginBtn = createButton("Log In", new Color(34, 139, 34));
        loginBtn.setBounds(180, 180, 140, 40);
        bgPanel.add(loginBtn);

        JButton reportBtn = createButton("View Reports", new Color(178, 34, 34));
        reportBtn.setBounds(180, 240, 140, 40);
        bgPanel.add(reportBtn);

        // --- Dark Mode Toggle ---
        JToggleButton themeToggle = new JToggleButton("Dark Mode");
        themeToggle.setBounds(180, 300, 140, 35);
        themeToggle.setSelected(control.isDarkMode());
        bgPanel.add(themeToggle);

        // --- Button Actions ---
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
            control.showReportView();
        });
        themeToggle.addActionListener(e -> {
            control.setDarkMode(themeToggle.isSelected());
            applyTheme(control, bgPanel, title, subtitle, signUpBtn, loginBtn, reportBtn, themeToggle);
        });

        // Apply theme initially
        applyTheme(control, bgPanel, title, subtitle, signUpBtn, loginBtn, reportBtn, themeToggle);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Tahoma", Font.BOLD, 16));
        return btn;
    }

    private void applyTheme(Control control, JPanel panel, JLabel title, JLabel subtitle,
                            JButton b1, JButton b2, JButton b3, JToggleButton toggle) {
        Color bg = control.isDarkMode() ? Color.DARK_GRAY : Color.WHITE;
        Color fg = control.isDarkMode() ? Color.WHITE : Color.BLACK;

        panel.setBackground(bg);
        title.setForeground(fg);
        subtitle.setForeground(control.isDarkMode() ? Color.LIGHT_GRAY : Color.DARK_GRAY);

        b1.setForeground(Color.WHITE);
        b2.setForeground(Color.WHITE);
        b3.setForeground(Color.WHITE);

        if (control.isDarkMode()) {
            b1.setBackground(b1.getBackground().darker());
            b2.setBackground(b2.getBackground().darker());
            b3.setBackground(b3.getBackground().darker());
            toggle.setBackground(Color.GRAY);
            toggle.setForeground(Color.WHITE);
        } else {
            b1.setBackground(new Color(70, 130, 180));
            b2.setBackground(new Color(34, 139, 34));
            b3.setBackground(new Color(178, 34, 34));
            toggle.setBackground(Color.LIGHT_GRAY);
            toggle.setForeground(Color.BLACK);
        }
    }
}
