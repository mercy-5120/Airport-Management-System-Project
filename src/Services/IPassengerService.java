package Services;

import Models.Booking;
import Models.Passenger;
import java.util.List;

public interface IPassengerService {
    void registerPassenger(Passenger passenger);
    Passenger getPassengerById(int id);
    Passenger getPassengerByEmail(String email);
    List<Passenger> getAllPassengers();
    List<Booking> viewBookingHistory(String passangerId);
}
