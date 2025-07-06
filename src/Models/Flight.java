package Models;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Flight {
    private int flightID;
    private String flightNo;
    private String origin;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private LocalDate date;
    private int capacity;
    private BigDecimal price;

    public Flight(String flightNo, String origin, String destination, String departureTime, int availableSeats) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.capacity = availableSeats;
    }

    public Flight(String flightNo, String origin, String departureTime, String destination, String arrivalTime, LocalDate date, int capacity, BigDecimal price) {
        this.flightNo = flightNo;
        this.origin = origin;
        this.departureTime = departureTime;
        this.destination = destination;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.capacity = capacity;
        this.price = price;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


}
