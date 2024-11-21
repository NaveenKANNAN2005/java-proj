import java.time.LocalDate;

public class Attendance {
    private int id;
    private int employeeId;
    private LocalDate attendanceDate;
    private String status;

    public Attendance(int id, int employeeId, LocalDate attendanceDate, String status) {
        this.id = id;
        this.employeeId = employeeId;
        this.attendanceDate = attendanceDate;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public LocalDate getAttendanceDate() { return attendanceDate; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Attendance [ID=" + id + ", Employee ID=" + employeeId +
               ", Date=" + attendanceDate + ", Status=" + status + "]";
    }
}
