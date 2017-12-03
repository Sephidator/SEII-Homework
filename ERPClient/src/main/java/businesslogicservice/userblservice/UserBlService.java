package main.java.businesslogicservice.userblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface UserBlService {
    public ArrayList<UserVO> getUserList(UserQueryVO query);

    public ResultMessage addUser(UserVO vo);

    public ResultMessage editUser(UserVO vo);

    public ResultMessage deleteUser(UserVO vo);

    public String getID();
}
