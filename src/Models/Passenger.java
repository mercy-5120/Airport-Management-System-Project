package Models;

public class Passenger extends User{
    public Passenger(String fullname, String email, String password) {
        super(fullname, email, password,Role.passenger);
    }

    public Passenger(int userId, String fullname, String email, String password) {
        super(userId, fullname, email, password, Role.passenger);
    }
}
