package Repositories;

import Models.Role;
import Models.User;
import dbUtil.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserRepository implements Services.UserService {
    @Override
    public User loginUser(String email, String password) {
        User user = findbyEmail(email);
        if (user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public User findbyEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("password"),
                        Role.valueOf(rs.getString("role").toUpperCase())
                );
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println("FindByEmail error: " + e.getMessage());
            return null;
        }
    }
}
