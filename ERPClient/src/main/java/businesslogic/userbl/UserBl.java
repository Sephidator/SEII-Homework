package businesslogic.userbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.userblservice.UserBlService;
import vo.user.UserQueryVO;
import vo.user.UserVO;

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
