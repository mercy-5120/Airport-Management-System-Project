package Repositories;

import Models.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository  {
    private Connection conn;


    public FlightRepository(Connection conn) {
        this.conn=conn;
    }

    public void addFlight(Flight flight) {
        String sql = "INSERT INTO flight(flight_number,departure_time,arrival_time,origin,destination,date,available_seats,price) VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, flight.getFlightNo());
            stmt.setString(2,flight.getDepartureTime());
            stmt.setString(3,flight.getArrivalTime());
            stmt.setString(4, flight.getOrigin());
            stmt.setString(5, flight.getDestination());
            stmt.setDate(6, Date.valueOf(flight.getDate()));
            stmt.setInt(7, flight.getCapacity());
            stmt.setBigDecimal(8, flight.getPrice());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error adding flight: " + e.getMessage());
        }
    }


    public void updateFlight(Flight flight) {
        String sql = "UPDATE flight SET flight_number=?,departure_time=?,arrival_time=?,origin=?,destination=?,date=?,available_seats=?,price=? WHERE flight_id=?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1, flight.getFlightNo());
            stmt.setString(2,flight.getDepartureTime());
            stmt.setString(3,flight.getArrivalTime());
            stmt.setString(4, flight.getOrigin());
            stmt.setString(5, flight.getDestination());
            stmt.setDate(6, Date.valueOf(flight.getDate()));
            stmt.setInt(7, flight.getCapacity());
            stmt.setBigDecimal(8, flight.getPrice());
            stmt.setInt(9, flight.getFlightID());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error updating flight: " + e.getMessage());
        }

    }


    public void deleteFlight(Flight flight) {
        String sql="DELETE FROM flight WHERE flight_number=?";
        try(PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,flight.getFlightNo());
            stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException("Error deleting flight: " + e.getMessage());
        }
    }


    public List<Flight> getAllFlights() {
        List<Flight> flights=new ArrayList<>();
        String sql="SELECT * FROM flight";
        try(PreparedStatement psmt=conn.prepareStatement(sql);
        ResultSet rs= psmt.executeQuery()){
            while(rs.next()){
                Flight flight=new Flight(
                        rs.getString("flight_number"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getString("origin"),
                        rs.getString("destination"),
                        rs.getDate("date").toLocalDate(),
                        rs.getInt("available_seats"),
                        rs.getBigDecimal("price")

                );
                flights.add(flight);
            }

        }catch (SQLException ex){
            ex.printStackTrace();
            throw new RuntimeException("Error getting all flights: " + ex.getMessage());
        }

        return flights;
    }

    public void updateAvailableSeats(String flightId) throws SQLException {
        String sql = "UPDATE flight SET available_seats=available_seats-1 WHERE flight_number=? and available_seats>0";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, flightId);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SQLException("No rows updated; either flight ID invalid or no seats left.");
            }
        }
    }

    public Flight getFlight(String flightNo) throws SQLException {
        String sql = "SELECT * FROM flight WHERE flight_number=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, flightNo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Flight(
                            rs.getString("flight_number"),
                            rs.getString("origin"),
                            rs.getString("destination"),
                            rs.getString("departure_time"),
                            rs.getInt("available_seats")
                    );
                }
            }
        }
        return null;
    }
}