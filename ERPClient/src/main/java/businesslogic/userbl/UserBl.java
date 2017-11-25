package main.java.businesslogic.userbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class UserBl implements UserBlService,UserTool {
    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage addUser(UserVO vo) {
        return null;
    }

    @Override
    public ResultMessage editUser(UserVO vo) {
        return null;
    }

    @Override
    public ResultMessage deleteUser(UserVO vo) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }
}
