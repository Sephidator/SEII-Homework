package main.java.businesslogicservice.loginblservice;

import main.java.vo.user.UserVO;

public interface LoginBlService {
    public UserVO login(String ID, String password);
}
