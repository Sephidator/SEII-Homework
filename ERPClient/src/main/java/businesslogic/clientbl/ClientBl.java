package main.java.businesslogic.clientbl;

import main.java.businesslogic.messagebl.MessageBl;
import main.java.businesslogic.messagebl.MessageTool;
import main.java.businesslogic.userbl.UserBl;
import main.java.businesslogic.userbl.UserTool;
import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.datafactory.clientdatafactory.ClientDataFactory;
import main.java.dataservice.clientdataservice.ClientDataService;
import main.java.po.client.ClientPO;
import main.java.po.client.ClientQueryPO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.message.MessageVO;
import main.java.vo.user.UserQueryVO;
import main.java.vo.user.UserVO;

import java.util.ArrayList;

public class ClientBl implements ClientBlService, ClientTool {
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @return: 返回ArrayList<ClientVO>的客户列表
     */
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) throws Exception {
        ArrayList<ClientVO> clientVOS = new ArrayList<>();
        ArrayList<ClientPO> clientPOS;

        /*将ClientQueryVO转为ClientQueryPO*/
        ClientQueryPO clientQueryPO = null;
        if (query != null) {
            clientQueryPO = query.getClientQueryPO();
        }

        /*调用ClientDataFactory得到ArrayList<ClientPO>的客户列表*/
        ClientDataService clientDataService = ClientDataFactory.getService();
        clientPOS = clientDataService.finds(clientQueryPO);

        /*ArrayList<ClientPO>以后转成ArrayList<ClientVO>*/
        for (ClientPO clientPO : clientPOS) {
            clientVOS.add(new ClientVO(clientPO));
        }

        return clientVOS;
    }

    /**
     * @version: 1
     * @date:
     * @param: [clientID] 查询的的客户对象的ID，用于查询数据库中该客户数据
     * @return: ClientVO的客户信息
     */
    @Override
    public ClientVO find(String clientID) throws Exception {
        if(clientID.equals("")){
            ClientPO clientPO=new ClientPO();
            return new ClientVO(clientPO);
        }
        else{
            /*调用ClientDataFactory得到ClientPO的客户*/
            ClientDataService clientDataService = ClientDataFactory.getService();
            ClientPO clientPO = clientDataService.find(clientID);
            return new ClientVO(clientPO);
        }
    }

    /**
     * @version: 1
     * @date:
     * @param: [clientVO] 增加的的客户对象，用于增加数据库中该客户数据
     * @return: 返回String的增加的客户ID
     */
    @Override
    public String addClient(ClientVO clientVO) throws Exception {
        String id = "";

        /*将ClientVO转成ClientPO*/
        ClientPO clientPO = clientVO.getClientPO();

        /*调用ClientDataFactory得到增加的客户ID*/
        ClientDataService clientDataService = ClientDataFactory.getService();
        id = clientDataService.insert(clientPO);

        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [client] 修改的的客户对象，用于修改数据库中该客户数据
     * @return:
     */
    @Override
    public void editClient(ClientVO client) throws Exception {
        /*将ClientVO转成ClientPO*/
        ClientPO clientPO = client.getClientPO();

        /*调用ClientDataFactory完成客户数据的修改*/
        ClientDataService clientDataService = ClientDataFactory.getService();
        clientDataService.update(clientPO);

        //增加：如果应收超过应收额度提醒财务人员去制定付款单还款
        if(clientPO.getReceivable() >= clientPO.getReceivableLimit()){
            MessageTool messageTool = new MessageBl();
            String mess = "当前客户 "+clientPO.getName()+" 应收（"+clientPO.getReceivable()
                    +"),已经大于或等于应收额度（"+clientPO.getReceivableLimit()+").现请你制定付款单还款，客户联系方式："+clientPO.getPhone()+"（系统消息）";
            //任意指定一个财务人员
            UserTool userTool = new UserBl();
            UserQueryVO userQueryVO = new UserQueryVO(null, "财务人员");
            ArrayList<UserVO> userVOS = userTool.getUserList(userQueryVO);
            int ran = (int) (Math.random() * (userVOS.size() - 0 + 1));
            MessageVO messageVO = new MessageVO(userVOS.get(ran),userVOS.get(ran),mess);
            messageTool.addMessage(messageVO);
        }

    }

    /**
     * @version: 1
     * @date:
     * @param: [clientID] 删除的的客户对象的ID，用于删除数据库中该客户数据
     * @return:
     */
    @Override
    public void deleteClient(String clientID) throws Exception {
        /*调用ClientDataFactory完成对客户的删除*/
        ClientDataService clientDataService = ClientDataFactory.getService();
        clientDataService.delete(clientID);
    }

    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的用户查询对象
     * @return: 返回ArrayList<UserVO>的用户列表
     */
    @Override
    public ArrayList<UserVO> getUserList(UserQueryVO query) throws Exception {
        ArrayList<UserVO> userVOS;

        /*调用UserTool服务得到ArrayList<UserVO>的客户列表*/
        UserTool userTool = new UserBl();
        userVOS = userTool.getUserList(query);

        return userVOS;
    }
}
