package businesslogic_mock.userblmock;

import businesslogic.userbl.UserTool;
import vo.user.UserQueryVO;
import vo.user.UserVO;

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
