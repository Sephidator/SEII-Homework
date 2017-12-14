package main.java.businesslogic.loginbl;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.po.user.UserPO;
import main.java.vo.user.UserVO;

public class LoginBl implements LoginBlService{
    @Override
    public UserVO login(String jobName, String password)throws Exception {
        UserVO userVO=new UserVO();

        UserTool userTool=new UserBl();
        userVO=userTool.login(jobName,password);
        return userVO;
    }

    @Override
    public void logout(String userID) throws Exception {
        UserTool userTool=new UserBl();
        userTool.logout(userID);
    }
}
