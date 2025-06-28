package Services;

import Models.User;

public interface ILoginService {
 User loginUser(String email, String password);
}
