package client_blservicestub.logblservicestub;

import businesslogicservice.loginblservice.LoginBlService;
import vo.user.UserVO;

public class LoginBlServiceStub implements LoginBlService{
    @Override
    public UserVO login(String ID, String password) {
        UserVO user=new UserVO();
        user.setID("123");
        return user;
    }
}
