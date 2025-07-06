package Services;

import Models.Booking;
import Models.Passenger;
import java.util.List;

public interface IPassengerService {
    void registerPassenger(String fullname,String email, String password);
    Passenger getPassengerByEmail(String email);

}
