package client_blservicestub.userblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.userblservice.UserBlService;
import vo.UserVO;

import java.util.ArrayList;

public class UserBlServiceStub implements UserBlService {
    ArrayList<UserVO> vos = new ArrayList<>();
    UserVO vo = new UserVO(1, 33, false, "马冬梅", "mdm123456", "queen", null);

    @Override
    public ArrayList<UserVO> show() {
        vos.clear();
        vos.add(vo);
        return vos;
    }

    @Override
    public ResultMessage add(UserVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(UserVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(UserVO vo) {
        return ResultMessage.success;
    }
}
