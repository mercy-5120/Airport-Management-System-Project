package Services;

import Models.Flight;
import Repositories.FlightRepository;

import java.util.List;

public class FlightService implements IFlightService{
    private final FlightRepository flightRepository;
     public FlightService(FlightRepository flightRepository) {
         this.flightRepository = flightRepository;
     }

    @Override
    public List<Flight> getAllFlights() {
        return flightRepository.getAllFlights();
    }
}
