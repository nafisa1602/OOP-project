import javax.swing.*;

public class LoginView {
    public LoginView(Control control) {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel rollLabel = new JLabel("Enter Roll:");
        rollLabel.setBounds(50, 40, 100, 25);
        frame.add(rollLabel);

        JTextField rollField = new JTextField();
        rollField.setBounds(140, 40, 180, 25);
        frame.add(rollField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(140, 90, 100, 30);
        frame.add(loginButton);

        loginButton.addActionListener(e -> {
            String roll = rollField.getText().trim();
            if (roll.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter your roll.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            control.loginStudent(roll);
            frame.dispose();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
