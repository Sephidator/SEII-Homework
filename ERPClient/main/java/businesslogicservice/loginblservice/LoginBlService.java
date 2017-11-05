package businesslogicservice.loginblservice;

import vo.user.UserVO;

public interface LoginBlService {
    public UserVO login(String ID, String password);
}
