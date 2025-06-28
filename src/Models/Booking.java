package Models;

import java.sql.Timestamp;

public class Booking {
    private String bookingId;
    private String passengerId;
    private String flightId;
    private Timestamp bookingTime;

    public Booking(String bookingId, String passengerId, String flightId, Timestamp bookingTime) {
        this.bookingId = bookingId;
        this.passengerId = passengerId;
        this.flightId = flightId;
        this.bookingTime = bookingTime;
    }

    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerId() {
        return passengerId;
    }
    public void setPassengerId(String passengerId) {
        this.passengerId = passengerId;
    }
    public String getFlightId() {
        return flightId;
    }
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public Timestamp getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(Timestamp bookingTime) {
        this.bookingTime = bookingTime;
    }
}
