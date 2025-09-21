import java.io.Serializable;
import java.time.LocalDate;

public class AttendanceRecord implements Serializable {
    private final static long serialVersionUID = 1L;
    private int present;
    private int totalClass;
    private LocalDate lastMarkedDate; // Tracks last date marked

    public AttendanceRecord() {
        this.present = 0;
        this.totalClass = 0;
        this.lastMarkedDate = null;
    }

    public int getPresent() { return present; }
    public int getTotalClass() { return totalClass; }
    public LocalDate getLastMarkedDate() { return lastMarkedDate; }

    // Returns false if already marked today
    public boolean markPresent() {
        LocalDate today = LocalDate.now();
        if(today.equals(lastMarkedDate)) return false;
        present++;
        totalClass++;
        lastMarkedDate = today;
        return true;
    }

    public boolean markAbsent() {
        LocalDate today = LocalDate.now();
        if(today.equals(lastMarkedDate)) return false;
        totalClass++;
        lastMarkedDate = today;
        return true;
    }

    public double getPercentage() {
        if(totalClass == 0) return 0.0;
        return (present * 100.0) / totalClass;
    }
}
