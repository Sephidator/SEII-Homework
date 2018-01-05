package main.java.businesslogic_mock.userblmock;

import main.java.businesslogic.userbl.UserTool;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class UserToolMock implements UserTool {

    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public UserVO find(String userID) throws Exception {
        return new UserVO();
    }

    @Override
    public UserVO login(String jobName, String password) throws Exception {
        return new UserVO();
    }

    @Override
    public void logout(String userID) throws Exception {

    }
}
