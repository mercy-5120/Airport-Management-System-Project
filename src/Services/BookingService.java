package Services;

import Models.Booking;
import Models.Flight;
import Repositories.BookingRepository;
import Repositories.FlightRepository;

import java.sql.SQLException;
import java.util.List;

public class BookingService implements IBookingService {
    private final BookingRepository bookingRepo;
    private final FlightRepository flightRepo;

    public BookingService(BookingRepository bookingRepo, FlightRepository flightRepo) {
        this.bookingRepo = bookingRepo;
        this.flightRepo = flightRepo;
    }

    @Override
    public boolean bookFlight(int userId, String flightId) throws SQLException {
        Flight flight = flightRepo.getFlight(flightId);
        if (flight != null && flight.getCapacity() > 0) {
            bookingRepo.addBooking(userId, flightId);
            flightRepo.updateAvailableSeats(flightId);
            return true;
        }
        return false;
    }

    @Override
    public boolean cancelBooking(int userId, String flightId) throws SQLException {
        Flight flight = flightRepo.getFlight(flightId);
        if (flight != null) {
            bookingRepo.cancelBooking(userId, flightId);
            flightRepo.updateAvailableSeats(flightId);
            return true;
        }
        return false;
    }


    @Override
    public List<Booking> viewBookingHistory(int userId) throws SQLException {
        return bookingRepo.getBookingsForUser(userId);
    }


}
