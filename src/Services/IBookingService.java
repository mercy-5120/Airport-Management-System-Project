package Services;

import Models.Booking;

import java.sql.SQLException;
import java.util.List;

public interface IBookingService {
    boolean bookFlight(int userId, String flightId) throws SQLException;
    boolean cancelBooking(int userId, String flightId) throws SQLException;


    List<Booking> viewBookingHistory(int userId) throws SQLException;
}
