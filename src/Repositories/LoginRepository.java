package Repositories;

import Models.Role;
import Models.User;
import PasswordUtil.PasswordUtil;
import Services.ILoginService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepository implements ILoginService {
    private final Connection conn;

    public LoginRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public User loginUser(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String hashedPassword = rs.getString("password");
                    if (PasswordUtil.checkPassword(password, hashedPassword)) {
                        Role role = Role.valueOf(rs.getString("role"));
                        return new User(
                                rs.getString("user_id"),
                                rs.getString("fullname"),
                                rs.getString("email"),
                                role
                        );
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
