package Repositories;

import Models.Flight;
import Models.Passenger;
import Models.Role;
import Services.IPassengerService;
import dbUtil.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepository  {
    private Connection conn;

    public PassengerRepository(Connection conn) {
        this.conn=conn;
    }


    public boolean addPassenger(Passenger passenger) {
        String sql = "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, passenger.getFullname());
            stmt.setString(2, passenger.getEmail());
            stmt.setString(3, passenger.getPassword());

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            System.out.println("Registration error: " + e.getMessage());
            return false;
        }
    }

    public Passenger getPassengerByEmail(String email) {
        String sql = "SELECT user_id, fullname, email, password FROM users WHERE email=? AND role='passenger'";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Passenger passenger = new Passenger();
                    passenger.setUserId(rs.getInt("user_id"));
                    passenger.setFullname(rs.getString("fullname"));
                    passenger.setEmail(rs.getString("email"));
                    passenger.setPassword(rs.getString("password")); // ✅ Required for login check
                    passenger.setRole(Role.passenger);               // ✅ Required for role-based UI
                    return passenger;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public List<Passenger> getAllPassengers() {
        List<Passenger> passengers=new ArrayList<>();
        String sql="SELECT * FROM users WHERE role='PASSENGER'";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                passengers.add(new Passenger(rs.getInt("user_id"),rs.getString("fullname"),rs.getString("email"),rs.getString("password")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengers;
    }



}
