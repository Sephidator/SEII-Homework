package main.java.businesslogicservice.loginblservice;

import main.java.vo.user.UserVO;

public interface LoginBlService {
    public UserVO login(String jobName, String password) throws Exception;

    public void logout(String userID) throws Exception;
}
