package SkyportManager;

import Repositories.*;
import Services.*;

import java.sql.Connection;

public class SkyPortManager {
    private final IAdminService adminService;
    private final ILoginService loginService;
    private final IPassengerService passengerService;
    private final IFlightService flightService;
    private final IBookingService bookingService;

    public SkyPortManager(Connection conn) {
        FlightRepository flightRepository = new FlightRepository(conn);
        PassengerRepository passengerRepository = new PassengerRepository(conn);
        AdminRepository adminRepository = new AdminRepository(conn);
        LoginRepository loginRepository = new LoginRepository(conn);
        BookingRepository bookingRepository = new BookingRepository(conn);

        this.adminService = new AdminService(flightRepository, passengerRepository);
        this.loginService = new LoginService(adminRepository, passengerRepository, loginRepository);
        this.passengerService = new PassengerService(passengerRepository, flightRepository);
        this.flightService = new FlightService(flightRepository);
        this.bookingService = new BookingService(bookingRepository, flightRepository); // ✅ Correctly initialized
    }

    public IAdminService getAdminService() { return adminService; }
    public ILoginService getLoginService() { return loginService; }
    public IPassengerService getPassengerService() { return passengerService; }
    public IFlightService getFlightService() { return flightService; }
    public IBookingService getBookingService() { return bookingService; }
}
