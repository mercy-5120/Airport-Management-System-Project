package Services;

import Models.Flight;
import Models.Passenger;
import Repositories.FlightRepository;
import Repositories.PassengerRepository;

import java.util.List;

public class AdminService implements IAdminService{
    private final FlightRepository flightRepository;
    private final PassengerRepository passengerRepository;

    public AdminService(FlightRepository flightRepository, PassengerRepository passengerRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
    }

    @Override
    public void addFlight(Flight flight) {
        flightRepository.addFlight(flight);
    }

    @Override
    public void updateFlight(Flight flight) {
        flightRepository.updateFlight(flight);
    }

    @Override
    public void deleteFlight(Flight flight) {
        flightRepository.deleteFlight(flight);
    }

    @Override
    public List<Passenger> getAllPassengers() {
        return passengerRepository.getAllPassengers();
    }
}
