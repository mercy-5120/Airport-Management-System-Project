package Repositories;

import Models.Admin;
import Models.Passenger;
import Models.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
private final Connection conn;

public AdminRepository(Connection conn) {
    this.conn=conn;
}

    public Admin findAdmin(String email)  {
        String sql = "SELECT * FROM users WHERE email=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Admin(rs.getString("user_id"), rs.getString("fullname"), rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
