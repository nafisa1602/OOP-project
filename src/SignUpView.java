import javax.swing.*;
import java.awt.*;

public class SignUpView {
    public SignUpView(Control control) {
        JFrame frame = new JFrame("Sign Up");
        frame.setSize(450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JPanel bgPanel = new JPanel();
        bgPanel.setLayout(null);
        bgPanel.setBounds(0,0,450,300);
        frame.add(bgPanel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 40, 80, 25);
        bgPanel.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(140, 40, 200, 25);
        bgPanel.add(nameField);

        JLabel rollLabel = new JLabel("Roll:");
        rollLabel.setBounds(50, 90, 80, 25);
        bgPanel.add(rollLabel);

        JTextField rollField = new JTextField();
        rollField.setBounds(140, 90, 200, 25);
        bgPanel.add(rollField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(140, 150, 100, 30);
        bgPanel.add(submitButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(260, 150, 80, 30);
        bgPanel.add(backButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            if(name.isEmpty() || roll.isEmpty()){
                JOptionPane.showMessageDialog(frame, "Fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            control.registerStudent(name, roll);
            frame.dispose();
            control.showMainMenu();
        });

        backButton.addActionListener(e -> {
            frame.dispose();
            control.showMainMenu();
        });

        applyTheme(control, bgPanel, nameLabel, rollLabel, submitButton, backButton);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void applyTheme(Control control, JPanel panel, JLabel l1, JLabel l2, JButton... buttons){
        panel.setBackground(control.isDarkMode() ? Color.DARK_GRAY : Color.WHITE);
        Color fg = control.isDarkMode() ? Color.WHITE : Color.BLACK;
        l1.setForeground(fg);
        l2.setForeground(fg);
        for(JButton b: buttons){
            b.setForeground(fg);
            if(control.isDarkMode()) b.setBackground(b.getBackground().darker());
        }
    }
}
