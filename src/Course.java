import javax.swing.*;

public class Course {
    JFrame couseFrame;
    public Course(String roll){
        couseFrame = new JFrame("Courses");
        couseFrame.setSize(400,200);
        couseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        couseFrame.setLayout(null);

        JLabel course= new JLabel("Select Course:");
        course.setBounds(10,20,100,25);
        couseFrame.add(course);

        String[] courses={"select","1203","1206","1211","1212","1215","1216","1206L","1211L","1215L"};
        JComboBox<String> courseBox = new JComboBox<>(courses);
        courseBox.setBounds(150,20,100,25);
        couseFrame.add(courseBox);

        JButton next= new JButton("next");
        next.setBounds(160,100,80,25);
        couseFrame.add(next);

        

        couseFrame.setVisible(true);
    }
}
