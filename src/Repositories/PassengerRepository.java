package Repositories;

import Models.Flight;
import Models.Passenger;
import dbUtil.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class PassengerRepository extends UserRepository implements Services.PassengerService {


    @Override
    public boolean registerPassenger(Passenger passenger) {


        String sql = "INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    @Override
    public boolean bookFlight(String username, int flightId) {
        return false;
    }

    @Override
    public boolean cancelFlight(String username, int flightId) {
        return false;
    }

    @Override
    public List<Flight> getAllFlights() {
        return List.of();
    }
}
