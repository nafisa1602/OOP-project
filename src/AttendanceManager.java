import java.io.*;
import java.util.*;

public class AttendanceManager {
    private static final String FILE_NAME = "students.dat";
    private Map<String, Student> students;

    public AttendanceManager() {
        students = loadStudents();
        students = new TreeMap<>(students); // Ensure an in-memory map is sorted
    }

    public synchronized boolean addStudent(Student student){
        if(students.containsKey(student.getRoll())){
            return false;
        }
        students.put(student.getRoll(), student);
        saveStudents();
        return true;
    }

    public synchronized Student getStudent(String roll){
        return students.get(roll);
    }

    public synchronized void updateStudent(Student student){
        students.put(student.getRoll(), student);
        saveStudents();
    }

    private void saveStudents(){
        // Convert to TreeMap to ensure sorting
        Map<String, Student> sorted = new TreeMap<>(students);
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            out.writeObject(sorted);
        } catch (IOException e){
            e.printStackTrace();
        }
        students = sorted; // Update in-memory sorted map
    }

    @SuppressWarnings("unchecked")
    private Map<String, Student> loadStudents(){
        File file = new File(FILE_NAME);
        if (!file.exists()){
            return new TreeMap<>();
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            return new TreeMap<>((Map<String, Student>) in.readObject());
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new TreeMap<>();
        }
    }

    public synchronized Map<String, Student> getAllStudents(){
        return new TreeMap<>(students);
    }
}
