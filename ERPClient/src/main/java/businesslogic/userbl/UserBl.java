package main.java.businesslogic.userbl;

import main.java.businesslogicservice.userblservice.UserBlService;
import main.java.datafactory.userdatafactory.UserDataFactory;
import main.java.dataservice.userdataservice.UserDataService;
import main.java.po.user.UserPO;
import main.java.po.user.UserQueryPO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class UserBl implements UserBlService,UserTool {

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的用户查询对象
     * @return: 返回ArrayList<UserVO>的用户列表
     */
    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception{

        /*将UserQueryVO转换为UserQueryPO*/
        UserQueryPO userQueryPO = null;
        if(query != null)
            userQueryPO = query.getUserQueryPO();

        /*调用UserDataService.find服务得到ArrayList<UserPO>的用户列表*/
        UserDataService userDataService = UserDataFactory.getService();
        ArrayList<UserPO> userPOS = userDataService.finds(userQueryPO);

        /*ArrayList<UserPO>以后转成ArrayList<UserVO>*/
        ArrayList<UserVO> userVOS=new ArrayList<>();
        for(UserPO userPO:userPOS){
            userVOS.add(new UserVO(userPO));
        }

        return userVOS;
    }

    @Override
    public UserVO find(String userID) throws Exception {
        if(userID.equals("")){
            UserPO userPO=new UserPO();
            return new UserVO(userPO);
        }
        else{
            UserDataService userDataService = UserDataFactory.getService();
            UserPO userPO = userDataService.find(userID);
            return new UserVO(userPO);
        }
    }

    @Override
    public UserVO login(String jobName, String password) throws Exception {

        /*dataservice*/
        UserDataService userDataService = UserDataFactory.getService();
        UserPO userPO = userDataService.login(jobName,password);
        return new UserVO(userPO);
    }

    @Override
    public void logout(String userID) throws Exception {

        /*dataserviceStub*/
        UserDataService userDataService = UserDataFactory.getService();
        userDataService.logout(userID);
    }

    /**
     * @version: 1
     * @date:
     * @param: [vo] 包含待增加的用户对象
     * @return: 返回String的增加用户的ID
     */
    @Override
    public String addUser(UserVO vo) throws Exception{

        /*将UserVO转换为UserPO*/
        UserPO userPO=vo.getUserPO();

        /*调用UserDataService.insert服务得到String的用户ID*/
        UserDataService userDataService = UserDataFactory.getService();
        String id = userDataService.insert(vo.getUserPO());

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [vo] 包含待修改的用户对象
     * @return:
     */
    @Override
    public void editUser(UserVO vo) throws Exception{
        /*将UserVO转换为UserPO*/
        UserPO userPO=vo.getUserPO();

        /*调用UserDataService.update服务完成对用户的修改*/
        UserDataService userDataService = UserDataFactory.getService();
        userDataService.update(userPO);
    }

    /**
     * @version: 1
     * @date:
     * @param: [UserID] 包含待删除的用户对象的ID
     * @return:
     */
    @Override
    public void deleteUser(String UserID) throws Exception{
        /*调用UserDataService.delete服务完成对用户的删除*/
        UserDataService userDataService = UserDataFactory.getService();
        userDataService.delete(UserID);
    }

}
