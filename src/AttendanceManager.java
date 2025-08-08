import java.io.*;
import java.util.HashMap;
import java.util.Map;
public class AttendanceManager{
    private static final String FILE_NAME = "students.dat";
    private Map<String, Student> students;

    public AttendanceManager() {
        students = loadStudents();
    }

    // Thread-safe add student, returns false if roll exists
    public synchronized boolean addStudent(Student student){
        if(students.containsKey(student.getRoll())){
            return false;
        }
        students.put(student.getRoll(), student);
        saveStudents();
        return true;
    }

    // Thread-safe get student by roll
    public synchronized Student getStudent(String roll){
        return students.get(roll);
    }

    // Thread-safe update existing student
    public synchronized void updateStudent(Student student){
        students.put(student.getRoll(), student);
        saveStudents();
    }

    // Save students map to file using serialization
    private void saveStudents(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            out.writeObject(students);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    // Load students map from file or create empty map if no file
    @SuppressWarnings("unchecked")
    private Map<String, Student> loadStudents(){
        File file = new File(FILE_NAME);
        if (!file.exists()){
            return new HashMap<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            return (Map<String, Student>) in.readObject();
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new HashMap<>();
        }
    }

    // Return a copy of all students map to avoid exposing internal state
    public synchronized Map<String, Student> getAllStudents(){
        return new HashMap<>(students);
    }
}
