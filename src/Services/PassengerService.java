package Services;

import Models.Flight;
import Models.Passenger;
import Repositories.FlightRepository;
import Repositories.PassengerRepository;

import java.util.List;

public class PassengerService implements IPassengerService{
    private final PassengerRepository passengerRepository;
    private final FlightRepository flightRepository;

    public PassengerService(PassengerRepository passengerRepository, FlightRepository flightRepository) {
        this.passengerRepository = passengerRepository;
        this.flightRepository = flightRepository;
    }

    @Override
    public void registerPassenger(String fullname,String email, String password) {
        Passenger passenger=new Passenger(fullname,email,password);
        passengerRepository.addPassenger(passenger);
    }

    @Override
    public Passenger getPassengerByEmail(String email) {
        return passengerRepository.getPassengerByEmail(email);
    }




}
