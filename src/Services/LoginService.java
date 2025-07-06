package Services;

import Models.Admin;
import Models.Passenger;
import Models.User;
import Repositories.AdminRepository;
import Repositories.LoginRepository;
import Repositories.PassengerRepository;
import PasswordUtil.PasswordUtil;


public class LoginService implements ILoginService {
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
        Passenger passenger = passengerRepository.getPassengerByEmail(email);
        if (passenger != null && PasswordUtil.checkPassword(password, passenger.getPassword())) {
            return passenger;
        }

        Admin admin = adminRepository.findAdmin(email);
        if (admin != null) {
            System.out.println("Entered password hashed: " + PasswordUtil.encryptPassword(password));
            System.out.println("Stored password hash: " + admin.getPassword());
            if (PasswordUtil.checkPassword(password, admin.getPassword())) {
                return admin;
            }
        }

        return null; // Not found
    }
}
