import javax.swing.*;
public class Roll {
    public Roll() {
        JFrame frame = new JFrame("LOGIN");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel roll = new JLabel("Enter roll:");
        roll.setBounds(80, 50, 80, 25);
        frame.add(roll);

        JTextField rollField = new JTextField();
        rollField.setBounds(160, 50, 80, 25);
        frame.add(rollField);

        JButton next = new JButton("next");
        next.setBounds(160, 100, 80, 25);
        frame.add(next);

        next.addActionListener(e -> {
            String getRoll = rollField.getText();
            if (!getRoll.isEmpty()) {
                frame.dispose(); // Close current form
                new Course(getRoll); // Pass name to next form
            } else {
                JOptionPane.showMessageDialog(frame, "Please enter your roll.");
            }
        });
        frame.setVisible(true);
    }
}
