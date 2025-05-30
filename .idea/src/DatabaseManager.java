import java.sql.*;

public class DatabaseManager {
    private String DB_URL = "jdbc:mysql://localhost:3306/skyport_management?useSSL=false&serverTimezone=UTC";
    private String DB_USER = "root";
    private String DB_PASSWORD = "kodongklan23HH!.";
    private Connection conn;

    public DatabaseManager() {
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Error in connection: " + e.getMessage());

        }
    }

    public void insertUserCredentials(String fullname, String email, String password) {
        if (conn == null) {
            System.out.println("Database connection is not established.");
            return;
        }

        String sql = "INSERT INTO users(full_name, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, fullname);
            psmt.setString(2, email);
            psmt.setString(3, password);

            int rowsAffected = psmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Registration Successful");
            } else {
                System.out.println("Registration failed");
            }
        } catch (SQLException e) {
            System.out.println("Error in inserting credentials: " + e.getMessage());
        }
    }

    public String checkUserCredentials(String email, String password) {
        if (conn == null) {
            System.out.println("Database connection is not established.");
            return null;
        }

        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.setString(1, email);
            psmt.setString(2, password);

            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Error in retrieving data: " + e.getMessage());
            return null;
        }
    }
}
