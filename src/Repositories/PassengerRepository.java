package Repositories;

import Models.Flight;
import Models.Passenger;
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

    public Passenger getPassengerByEmail(String email)  {
        String sql = "SELECT * FROM users WHERE email=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Passenger(rs.getString("user_id"), rs.getString("fullname"), rs.getString("email"));
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
                passengers.add(new Passenger(rs.getString("user_id"), rs.getString("fullname"), rs.getString("email")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return passengers;
    }


}
