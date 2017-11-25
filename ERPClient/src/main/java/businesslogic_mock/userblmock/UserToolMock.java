package main.java.businesslogic_mock.userblmock;

import main.java.businesslogic.userbl.UserTool;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class UserToolMock implements UserTool {
    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) {
        ArrayList<UserVO> list = new ArrayList<>();
        list.add(new UserVO());
        list.add(new UserVO());
        return list;
    }
}
