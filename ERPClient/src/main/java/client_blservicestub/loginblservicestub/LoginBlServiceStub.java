package main.java.client_blservicestub.loginblservicestub;

import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.vo.user.UserVO;

public class LoginBlServiceStub implements LoginBlService {

    @Override
    public UserVO login(String jobName, String password) throws Exception {
        return new UserVO();
    }

    @Override
    public void logout(String userID) throws Exception {

    }
}
