package main.java.businesslogic.loginbl;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.loginblservice.LoginBlService;
import main.java.po.user.UserPO;
import main.java.vo.user.UserVO;

public class LoginBl implements LoginBlService{
    /**
     * @version: 1
     * @date:
     * @param: [jobName][password] 分别是输入的用户信息，用于更新数据库中该用户数据
     * @return: 返回UserVO的用户信息
     */
    @Override
    public UserVO login(String jobName, String password)throws Exception {
        UserVO userVO=new UserVO();

        UserTool userTool=new UserBl();
        userVO=userTool.login(jobName,password);
        return userVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [userID]是查询的用户ID，用于更新数据库中该用户数据
     * @return:
     */
    @Override
    public void logout(String userID) throws Exception {
        UserTool userTool=new UserBl();
        userTool.logout(userID);
    }
}
