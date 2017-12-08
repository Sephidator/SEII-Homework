package main.java.businesslogic.clientbl;

import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.data_stub.clientdataservicestub.ClientDataServiceStub;
import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ClientBl implements ClientBlService,ClientTool{
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
     */
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query)  throws Exception{
        ArrayList<ClientVO> clientVOS=new ArrayList<>();
        ArrayList<ClientPO> clientPOS=new ArrayList<>();

        /*将ClientQueryVO转为ClientQueryPO*/
        ClientQueryPO clientQueryPO=query.getClientQueryPO();

        /*调用ClientDataService.finds服务得到ArrayList<ClientPO>的客户列表*/

         /*调用dataservice的桩*/
        ClientDataService clientDataService=new ClientDataServiceStub();
        clientPOS=clientDataService.finds(clientQueryPO);

        /*ArrayList<ClientPO>以后转成ArrayList<ClientVO>*/
        for(ClientPO clientPO:clientPOS){
            clientVOS.add(new ClientVO(clientPO));
        }

        return clientVOS;
    }

    @Override
    public ClientVO find(String clientID) throws Exception {
        ClientVO clientVO=new ClientVO();
        ClientPO clientPO=new ClientPO();

        /*调用ClientDataService.find服务得到ClientPO的客户*/

        /*调用dataservice的桩*/
        ClientDataService clientDataService=new ClientDataServiceStub();
        clientPO=clientDataService.find(clientID);

        /*转换ClientPO*/
        clientVO=new ClientVO(clientPO);

        return clientVO;
    }

    /**
     * @version: 1
     * @date:
     * @param: [clientVO] 增加的的客户对象，用于增加数据库中该客户数据
     * @return: 返回String的增加的客户ID
     */
    @Override
    public String addClient(ClientVO clientVO)  throws Exception{
        String id="";

        /*将ClientVO转成ClientPO*/
        ClientPO clientPO=clientVO.getClientPO();

        /*调用ClientDataService.insert服务得到增加的客户ID*/

        /*调用dataservice的桩*/
        ClientDataService clientDataService=new ClientDataServiceStub();
        id=clientDataService.insert(clientPO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [client] 修改的的客户对象，用于修改数据库中该客户数据
     * @return:
     */
    @Override
    public void editClient(ClientVO client)  throws Exception{
        /*将ClientVO转成ClientPO*/
        ClientPO clientPO=client.getClientPO();

        /*调用ClientDataService.update服务完成客户数据的修改*/

        /*调用dataservice的桩*/
        ClientDataService clientDataService=new ClientDataServiceStub();
        clientDataService.update(clientPO);
    }

    /**
     * @version: 1
     * @date:
     * @param: [clientID] 删除的的客户对象的ID，用于删除数据库中该客户数据
     * @return:
     */
    @Override
    public void deleteClient(String clientID)  throws Exception{
        /*调用ClientDataService.delete服务完成对客户的删除*/

        /*调用dataservice的桩*/
        ClientDataService clientDataService=new ClientDataServiceStub();
        clientDataService.delete(clientID);
    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的用户查询对象
     * @return: 返回ArrayList<UserVO>的用户列表
     */
    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query)  throws Exception{
        ArrayList<UserVO> userVOS=new ArrayList<>();

        /*调用UserTool服务得到ArrayList<UserVO>的客户列表*/
        UserTool userTool=new UserBl();
        userVOS=userTool.getUserList(query);

        return userVOS;
    }
}
