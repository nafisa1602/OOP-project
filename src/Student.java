import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String name;
    private String roll;
    private String batch;
    private Map<String, AttendanceRecord> attendanceRecordMap;

    public Student(String name, String roll){
        if(name == null || name.isBlank() || roll == null || roll.isBlank()){
            throw new IllegalArgumentException("Name and Roll required");
        }
        this.name = name;
        this.roll = roll;
        this.batch = batchFromRoll(roll);
        this.attendanceRecordMap = new HashMap<>();
    }

    public String getName() { return name; }
    public String getRoll() { return roll; }
    public String getBatch() { return batch; }

    private String batchFromRoll(String roll) {
        Pattern pattern = Pattern.compile("(?i)(?:BSSE)?-?(\\d{2})\\d{2}");
        Matcher matcher = pattern.matcher(roll);
        if (matcher.matches()) return matcher.group(1);
        return "Unknown";
    }

    public void attendanceForCourse(String course, boolean present){
        AttendanceRecord record = attendanceRecordMap.get(course);
        if(record == null){
            record = new AttendanceRecord();
            attendanceRecordMap.put(course, record);
        }

        boolean success;
        if(present) success = record.markPresent();
        else success = record.markAbsent();

        if(!success){
            JOptionPane.showMessageDialog(null,
                    "Attendance already marked today for " + course,
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public AttendanceRecord getAttendanceRecord(String course){
        return attendanceRecordMap.getOrDefault(course, new AttendanceRecord());
    }

    public Map<String, AttendanceRecord> getAttendanceMap() {
        return attendanceRecordMap;
    }
}
