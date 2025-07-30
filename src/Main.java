import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame= new JFrame("IIT Attendance Tracker");
        mainFrame.setSize(400,500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        JButton signUp = new JButton("Sign Up");
        signUp.setBounds(150, 50, 85, 25);
        mainFrame.add(signUp);

        JButton logIn = new JButton("Log In");
        logIn.setBounds(150, 100, 85, 25);
        mainFrame.add(logIn);

        signUp.addActionListener(e -> {
                    mainFrame.dispose();
                   new SignUpInfo();
                } );
        logIn.addActionListener(e -> {
            mainFrame.dispose();
            new Roll();
        } );
        mainFrame.setVisible(true);
    }

}
