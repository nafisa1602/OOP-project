import javax.swing.*;

public class SignUpView {
    public SignUpView(Control control) {
        JFrame frame = new JFrame("Sign Up");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 40, 80, 25);
        frame.add(nameLabel);

        JTextField nameField = new JTextField();
        nameField.setBounds(140, 40, 180, 25);
        frame.add(nameField);

        JLabel rollLabel = new JLabel("Roll:");
        rollLabel.setBounds(50, 90, 80, 25);
        frame.add(rollLabel);

        JTextField rollField = new JTextField();
        rollField.setBounds(140, 90, 180, 25);
        frame.add(rollField);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(140, 140, 100, 30);
        frame.add(submitButton);

        submitButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();

            if (name.isEmpty() || roll.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            control.registerStudent(name, roll);
            frame.dispose();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
