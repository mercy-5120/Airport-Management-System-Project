package Repositories;

import Models.Booking;
import Models.Flight;
import Models.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingRepository {
    private final Connection conn;

    public BookingRepository(Connection conn) {
        this.conn = conn;
    }

    // Add new booking with default status 'CONFIRMED'
    public void addBooking(int userId, String flightNo) throws SQLException {
        String sql = "INSERT INTO booking (user_id, flight_number, status) VALUES (?, ?, 'CONFIRMED')";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);       // user_id (int)
            stmt.setString(2, flightNo);  // flight_number (String)
            stmt.executeUpdate();
        }
    }


    // Cancel an existing booking: update status to 'CANCELLED'
    public void cancelBooking(int userId, String flightNo) throws SQLException {
        String sql = "UPDATE booking SET status='CANCELLED' WHERE user_id=? AND flight_number=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setString(2, flightNo);
            stmt.executeUpdate();
        }
    }

    // Get all bookings for a user, including bookingId and status
    public List<Booking> getBookingsForUser(int userId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT b.booking_id, b.status, f.flight_number, f.origin, f.destination, " +
                "f.departure_time, f.arrival_time, f.date, f.available_seats, f.price " +
                "FROM booking b " +
                "JOIN flight f ON b.flight_number = f.flight_number " +
                "WHERE b.user_id=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Flight flight = new Flight(
                            rs.getString("flight_number"),
                            rs.getString("origin"),
                            rs.getString("departure_time"),
                            rs.getString("destination"),
                            rs.getString("arrival_time"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("available_seats"),
                            rs.getBigDecimal("price")
                    );

                    Booking booking = new Booking(
                            rs.getInt("booking_id"),
                            String.valueOf(userId),
                            rs.getString("flight_number"),
                            Status.valueOf(rs.getString("status"))
                    );
                    booking.setFlight(flight);
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }


}
