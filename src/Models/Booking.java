package Models;

public class Booking {
    private int bookingId;
    private String userId;
    private String flightNo;
    private Status status;
    private Flight flight;

    public Flight getFlight() { return flight; }
    public void setFlight(Flight flight) { this.flight = flight; }


    public Booking(int bookingId, String userId, String flightNo, Status status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightNo = flightNo;
        this.status = status;
    }

    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }



    public String getFlightNo() {
        return flightNo;
    }
    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
