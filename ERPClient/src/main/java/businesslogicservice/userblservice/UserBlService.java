package businesslogicservice.userblservice;

import businesslogic.blutility.ResultMessage;
import vo.user.UserQueryVO;
import vo.user.UserVO;

import java.util.ArrayList;

public interface UserBlService {
    public ArrayList<UserVO> getUserList(UserQueryVO query);

    public ResultMessage addUser(UserVO vo);

    public ResultMessage editUser(UserVO vo);

    public ResultMessage deleteUser(UserVO vo);

    public String getID();
}
