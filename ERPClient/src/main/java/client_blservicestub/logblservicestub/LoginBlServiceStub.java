package main.java.client_blservicestub.logblservicestub;

import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.vo.user.UserVO;

public class LoginBlServiceStub implements LoginBlService{
    @Override
    public UserVO login(String ID, String password) {
        UserVO user=new UserVO();
        user.setID("123");
        return user;
    }
}
