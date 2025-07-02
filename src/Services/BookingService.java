package Services;

import Models.Booking;
import Repositories.BookingRepository;

import java.util.List;

public class BookingService implements IBookingService {

    private BookingRepository bookingRepository;

    public BookingService() {
        this.bookingRepository = new BookingRepository();
    }

    @Override
    public void bookFlight(Booking booking) {
        bookingRepository.saveBooking(booking);
    }

    @Override
    public void cancelBooking(String bookingId) {
        bookingRepository.deleteBooking(bookingId);
    }

    @Override
    public Booking getBookingById(String bookingId) {
        return bookingRepository.getBookingById(bookingId);
    }

    @Override
    public List<Booking> getBookingsByPassengerId(String passengerId) {
        return bookingRepository.getBookingsByPassengerId(passengerId);
    }
}
