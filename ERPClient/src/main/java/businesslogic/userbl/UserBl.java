package main.java.businesslogic.userbl;

import main.java.businesslogicservice.userblservice.UserBlService;
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
    public ArrayList<UserVO> getUserList(UserQueryVO query) {
        ArrayList<UserVO> userVOS=new ArrayList<>();
        ArrayList<UserPO> userPOS=new ArrayList<>();

        /*将UserQueryVO转换为UserQueryPO*/
        UserQueryPO userQueryPO=query.getUserQueryPO();

        /*调用UserDataService.find服务得到ArrayList<UserPO>的用户列表*/

        /*ArrayList<UserPO>以后转成ArrayList<UserVO>*/
        for(UserPO userPO:userPOS){
            userVOS.add(new UserVO(userPO));
        }

        return userVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [vo] 包含待增加的用户对象
     * @return: 返回String的增加用户的ID
     */
    @Override
    public String addUser(UserVO vo) {
        String id="";

        /*将UserVO转换为UserPO*/
        UserPO userPO=vo.getUserPO();

        /*调用UserDataService.insert服务得到String的用户ID*/

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [vo] 包含待修改的用户对象
     * @return:
     */
    @Override
    public void editUser(UserVO vo) {
        /*将UserVO转换为UserPO*/
        UserPO userPO=vo.getUserPO();

        /*调用UserDataService.update服务完成对用户的修改*/
    }

    /**
     * @version: 1
     * @date:
     * @param: [UserID] 包含待删除的用户对象的ID
     * @return:
     */
    @Override
    public void deleteUser(String UserID) {
        /*调用UserDataService.delete服务完成对用户的删除*/
    }

}
