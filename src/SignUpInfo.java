import javax.swing.*;
import java.io.*;
public class SignUpInfo {

    public SignUpInfo() {
        JFrame signUPFrame = new JFrame("SIGN UP");
        signUPFrame.setSize(400, 500);
        signUPFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUPFrame.setLayout(null);
        signUPFrame.setVisible(true);

        JLabel name1 = new JLabel("Enter name:");
        name1.setBounds(80, 50, 80, 25);
        signUPFrame.add(name1);

        JTextField nameField = new JTextField();
        nameField.setBounds(160, 50, 80, 25);
        signUPFrame.add(nameField);

        JLabel roll1 = new JLabel("Enter roll:");
        roll1.setBounds(80, 150, 80, 25);
        signUPFrame.add(roll1);

        JTextField rollField = new JTextField();
        rollField.setBounds(160, 150, 80, 25);
        signUPFrame.add(rollField);

        JLabel batch1 = new JLabel("Enter batch:");
        batch1.setBounds(80, 250, 80, 25);
        signUPFrame.add(batch1);

        JTextField batchField = new JTextField();
        batchField.setBounds(160, 250, 80, 25);
        signUPFrame.add(batchField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(160, 300, 85, 25);
        signUPFrame.add(signUpButton);

        signUpButton.addActionListener(e -> {
            String name = nameField.getText();
            String roll = rollField.getText();
            String batch = batchField.getText();

            if (name.isEmpty() || roll.isEmpty() || batch.isEmpty()) {
                JOptionPane.showMessageDialog(signUPFrame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    FileWriter writer = new FileWriter( roll + "_data.txt", true); // true = append mode
                    writer.write("Name: " + name + "\n");
                    writer.write("Roll: " + roll + "\n");
                    writer.write("Batch: " + batch + "\n");
                    writer.close();

                    JOptionPane.showMessageDialog(signUPFrame, "Sign Up Saved Successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    signUPFrame.dispose();
                    new Roll();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(signUPFrame, "Error writing to file!", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        signUPFrame.setVisible(true);
    }
}
