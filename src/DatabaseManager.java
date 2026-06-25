import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    // =========================
    // DATABASE CONFIGURATION
    // =========================
    private static final String URL = "jdbc:mysql://localhost:3306/rps_project";
    private static final String USER = "root";
    private static final String PASSWORD = ""; 
    // =========================
    // GET CONNECTION METHOD
    // =========================
    public static Connection getConnection() throws SQLException {
        try {
            // Optional but safe for older setups
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}