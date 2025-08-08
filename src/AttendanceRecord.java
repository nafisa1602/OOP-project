import java.io.Serializable;
public class AttendanceRecord implements Serializable{
    private final static long serialVersionUID = 1L;
   private int present;
   private int totalClass;
   public AttendanceRecord(){
       this.present = 0;
       this.totalClass = 0;
   }

   public AttendanceRecord(int present, int totalClass){
       this.present = present;
       this.totalClass = totalClass;
   }

   public int getPresent(){
       return present;
   }

   public int getTotalClass(){
       return totalClass;
   }

   public void presentTracker(){
       present++;
       totalClass++;
   }

   public void absentTracker(){
       totalClass++;
   }

   public double getPercentage(){
       if(totalClass == 0){
           return 0.0;
       }
       return (present*100.0)/totalClass;
   }
}
