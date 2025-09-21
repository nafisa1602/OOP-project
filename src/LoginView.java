import javax.swing.*;
import java.awt.*;

public class LoginView {
    public LoginView(Control control) {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0,0,400,200);
        frame.add(bgPanel);

        JLabel rollLabel = new JLabel("Enter Roll:");
        rollLabel.setBounds(50, 40, 100, 25);
        bgPanel.add(rollLabel);

        JTextField rollField = new JTextField();
        rollField.setBounds(150, 40, 180, 25);
        bgPanel.add(rollField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 90, 100, 30);
        bgPanel.add(loginButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 90, 80, 30);
        bgPanel.add(backButton);

        loginButton.addActionListener(e -> {
            String roll = rollField.getText().trim();
            if(roll.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Enter roll", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            frame.dispose();
            control.loginStudent(roll);
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            control.showMainMenu();
        });

        applyTheme(control, bgPanel, rollLabel, loginButton, backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void applyTheme(Control control, JPanel panel, JLabel label, JButton... buttons){
        panel.setBackground(control.isDarkMode() ? Color.DARK_GRAY : Color.WHITE);
        Color fg = control.isDarkMode() ? Color.WHITE : Color.BLACK;
        label.setForeground(fg);
        for(JButton b: buttons){
            b.setForeground(fg);
            if(control.isDarkMode()) b.setBackground(b.getBackground().darker());
        }
    }
}
