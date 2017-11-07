package businesslogic.userbl;

import vo.user.UserQueryVO;
import vo.user.UserVO;

import java.util.ArrayList;

public interface UserTool {
    public ArrayList<UserVO> getUserList(UserQueryVO query);
}
