import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        AttendanceDAO attendanceDAO = new AttendanceDAOImpl();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Attendance Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. View Employee");
            System.out.println("5. Mark Attendance");
            System.out.println("6. View Attendance by Employee");
            System.out.println("7. View Attendance by Date");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Department: ");
                    String department = scanner.nextLine();
                    employeeDAO.addEmployee(new Employee(id, name, department));
                    System.out.println("Employee added successfully.");
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    id = scanner.nextInt();
                    Employee existingEmployee = employeeDAO.getEmployee(id);
                    if (existingEmployee != null) {
                        employeeDAO.updateEmployee(existingEmployee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter Employee ID to Delete: ");
                    id = scanner.nextInt();
                    employeeDAO.deleteEmployee(id);
                    System.out.println("Employee deleted successfully.");
                    break;

                case 4:
                    System.out.print("Enter Employee ID to View: ");
                    id = scanner.nextInt();
                    Employee employee = employeeDAO.getEmployee(id);
                    if (employee != null) {
                        System.out.println(employee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter Employee ID: ");
                    id = scanner.nextInt();
                    System.out.print("Enter Attendance Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.next());
                    System.out.print("Enter Status (Present/Absent): ");
                    String status = scanner.next();
                    attendanceDAO.markAttendance(id, date, status);
                    System.out.println("Attendance marked successfully.");
                    break;

                case 6:
                    System.out.print("Enter Employee ID to View Attendance: ");
                    id = scanner.nextInt();
                    List<Attendance> recordsByEmployee = attendanceDAO.getAttendanceByEmployee(id);
                    for (Attendance record : recordsByEmployee) {
                        System.out.println(record);
                    }
                    break;

                case 7:
                    System.out.print("Enter Date (YYYY-MM-DD) to View Attendance: ");
                    date = LocalDate.parse(scanner.next());
                    List<Attendance> recordsByDate = attendanceDAO.getAttendanceByDate(date);
                    for (Attendance record : recordsByDate) {
                        System.out.println(record);
                    }
                    break;

                case 8:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
