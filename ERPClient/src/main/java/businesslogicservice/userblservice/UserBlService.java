package main.java.businesslogicservice.userblservice;

import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface UserBlService {
    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception;

    public String addUser(UserVO vo) throws Exception;

    public void editUser(UserVO vo) throws Exception;

    public void deleteUser(String UserID) throws Exception;

}
