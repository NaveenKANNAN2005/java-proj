import java.time.LocalDate;
import java.util.List;

public interface AttendanceDAO {
    void markAttendance(int employeeId, LocalDate date, String status);
    List<Attendance> getAttendanceByEmployee(int employeeId);
    List<Attendance> getAttendanceByDate(LocalDate date);
}
