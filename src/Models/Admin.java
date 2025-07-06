package Models;

public class Admin extends  User {
    public Admin(int userId, String fullname, String email, String password, Role role) {
        super(fullname, email, password, Role.admin);
    }

    public Admin(int userId, String fullname, String email, String password) {
        super(userId, fullname, email, password, Role.admin);
    }
}
