import java.sql.*;
import java.util.ArrayList;
import java.util.*;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/EmployeeDB";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employees (id, name, department) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, employee.getId());
            stmt.setString(2, employee.getName());
            stmt.setString(3, employee.getDepartment());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding employee", e);
        }
    }

    @Override
    public void updateEmployee(Employee employee) {
        Scanner scanner = new Scanner(System.in);

        // Ask user what they want to update
        System.out.println("What do you want to update?");
        System.out.println("1. Name");
        System.out.println("2. Department");
        System.out.print("Choose an option: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        String sql = "";
        switch (choice) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                sql = "UPDATE Employees SET name = ? WHERE id = ?";
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, newName);
                    stmt.setInt(2, employee.getId());
                    stmt.executeUpdate();
                    System.out.println("Employee name updated successfully.");
                } catch (SQLException e) {
                    throw new RuntimeException("Error updating employee name", e);
                }
                break;

            case 2:
                System.out.print("Enter new department: ");
                String newDepartment = scanner.nextLine();
                sql = "UPDATE Employees SET department = ? WHERE id = ?";
                try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                     PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, newDepartment);
                    stmt.setInt(2, employee.getId());
                    stmt.executeUpdate();
                    System.out.println("Employee department updated successfully.");
                } catch (SQLException e) {
                    throw new RuntimeException("Error updating employee department", e);
                }
                break;

            default:
                System.out.println("Invalid choice. No updates performed.");
                break;
        }
    }

    @Override
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM Employees WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    @Override
    public Employee getEmployee(int id) {
        String sql = "SELECT * FROM Employees WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching employee", e);
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Employees";
        List<Employee> employees = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                employees.add(new Employee(rs.getInt("id"), rs.getString("name"), rs.getString("department")));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all employees", e);
        }
        return employees;
    }
}
