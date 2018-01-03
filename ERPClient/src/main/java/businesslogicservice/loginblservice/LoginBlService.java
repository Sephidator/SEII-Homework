package main.java.businesslogicservice.loginblservice;

import main.java.vo.user.UserVO;

public interface LoginBlService {
    UserVO login(String jobName, String password) throws Exception;

    void logout(String userID) throws Exception;
}
