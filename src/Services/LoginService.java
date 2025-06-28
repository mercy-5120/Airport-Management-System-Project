package Services;

import Models.Admin;
import Models.Passenger;
import Models.User;
import Repositories.AdminRepository;
import Repositories.LoginRepository;
import Repositories.PassengerRepository;

public class LoginService implements ILoginService{
    private final AdminRepository adminRepository;
    private final PassengerRepository passengerRepository;
    private final LoginRepository loginRepository;

    public LoginService(AdminRepository adminRepository, PassengerRepository passengerRepository, LoginRepository loginRepository) {
        this.adminRepository = adminRepository;
        this.passengerRepository = passengerRepository;
        this.loginRepository = loginRepository;
    }

    @Override
    public User loginUser(String email, String password) {
        Admin admin=adminRepository.findAdmin(email);
        if(admin!=null) return admin;

        Passenger passenger=passengerRepository.getPassengerByEmail(email);
        if(passenger!=null) return passenger;

        return null;

    }
}
