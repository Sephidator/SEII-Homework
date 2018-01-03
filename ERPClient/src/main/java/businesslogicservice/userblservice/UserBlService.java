package main.java.businesslogicservice.userblservice;

import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface UserBlService {
    ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception;

    String addUser(UserVO vo) throws Exception;

    void editUser(UserVO vo) throws Exception;

    void deleteUser(String UserID) throws Exception;

}
