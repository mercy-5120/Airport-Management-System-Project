package Services;

import Models.Flight;
import Models.Passenger;

import java.util.List;

public interface IAdminService {
    void addFlight(Flight flight);
    void updateFlight(Flight flight);
    void deleteFlight(Flight flight);
    List<Passenger> getAllPassengers();

}
