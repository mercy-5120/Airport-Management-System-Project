package Services;

import Models.Booking;

import java.util.List;

public interface IBookingService {
    void bookFlight(Booking booking);
    void cancelBooking(String bookingId);
    Booking getBookingById(String bookingId);
    List<Booking> getBookingsByPassengerId(String passengerId);
}
