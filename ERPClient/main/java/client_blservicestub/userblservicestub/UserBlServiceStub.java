package client_blservicestub.userblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.userblservice.UserBlService;
import vo.user.UserQueryVO;
import vo.user.UserVO;

import java.util.ArrayList;

public class UserBlServiceStub implements UserBlService {
    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) {
        ArrayList<UserVO> list = new ArrayList<>();
        list.add(new UserVO());
        return list;
    }

    @Override
    public ResultMessage addUser(UserVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage editUser(UserVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deleteUser(UserVO vo) {
        return ResultMessage.success;
    }

    @Override
    public String getID() {
        return "678910";
    }
}
