package main.java.businesslogic.userbl;

import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface UserTool {
    ArrayList<UserVO> getUserList(UserQueryVO query)throws Exception;

    UserVO find(String userID)throws Exception;//通过UserID查找某个用户

    UserVO login(String jobName, String password) throws Exception;

    void logout(String userID) throws Exception;
}
