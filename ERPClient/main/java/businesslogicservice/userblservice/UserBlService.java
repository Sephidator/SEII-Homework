package businesslogicservice.userblservice;

import businesslogic.blutility.ResultMessage;
import vo.user.UserVO;

import java.util.ArrayList;

public interface UserBlService {
    ArrayList<UserVO> show();

    ResultMessage add(UserVO vo);

    ResultMessage update(UserVO vo);

    ResultMessage delete(UserVO vo);
}
