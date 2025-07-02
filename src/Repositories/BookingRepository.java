package Repositories;

import Models.Booking;
//import dbUtil.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {

    private Connection conn;

    public BookingRepository() {
//        conn = DbConnection.getConnection();
    }

    public void saveBooking(Booking booking) {
        String sql = "INSERT INTO bookings (booking_id, passenger_id, flight_id, booking_time) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, booking.getBookingId());
            stmt.setString(2, booking.getPassengerId());
            stmt.setString(3, booking.getFlightId());
            stmt.setTimestamp(4, booking.getBookingTime());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBooking(String bookingId) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Booking getBookingById(String bookingId) {
        String sql = "SELECT * FROM bookings WHERE booking_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bookingId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Booking(
                        rs.getString("booking_id"),
                        rs.getString("passenger_id"),
                        rs.getString("flight_id"),
                        rs.getTimestamp("booking_time")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Booking> getBookingsByPassengerId(String passengerId) {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT * FROM bookings WHERE passenger_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, passengerId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getString("booking_id"),
                        rs.getString("passenger_id"),
                        rs.getString("flight_id"),
                        rs.getTimestamp("booking_time")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }
}
