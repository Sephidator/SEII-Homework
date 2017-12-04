package main.java.businesslogicservice.userblservice;

import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface UserBlService {
    public ArrayList<UserVO> getUserList(UserQueryVO query);

    public String addUser(UserVO vo);

    public void editUser(UserVO vo);

    public void deleteUser(String UserID);

}
