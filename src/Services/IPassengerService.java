package Services;

import Models.Flight;
import Models.Passenger;

import java.util.List;

public interface IPassengerService {
    void registerPassenger(String fullname,String email, String password);
    Passenger getPassengerByEmail(String email);

}

