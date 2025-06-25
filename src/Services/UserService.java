package Services;

import Models.User;

public interface UserService {
 User loginUser(String email, String password);
 User findbyEmail(String email);
}
