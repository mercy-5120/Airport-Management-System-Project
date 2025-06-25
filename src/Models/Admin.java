package Models;

public class Admin extends  User {
    public Admin(String fullname, String email, String password) {
        super(fullname, email, password, Role.ADMIN);
    }

    public Admin(int userId, String fullname, String email, String password) {
        super(userId, fullname, email, password, Role.ADMIN);
    }
}
