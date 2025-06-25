package Services;

import Models.Flight;
import Models.Passenger;

import java.util.List;

public interface PassengerService {
    boolean registerPassenger(Passenger passenger);
    boolean bookFlight(String username, int flightId);
    boolean cancelFlight(String username, int flightId);
   List<Flight>getAllFlights();
}

