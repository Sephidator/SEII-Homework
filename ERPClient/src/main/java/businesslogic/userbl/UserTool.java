package main.java.businesslogic.userbl;

import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public interface UserTool {
    public ArrayList<UserVO> getUserList(UserQueryVO query)throws Exception;

    public UserVO find(String userID)throws Exception;//通过UserID查找某个用户
}
