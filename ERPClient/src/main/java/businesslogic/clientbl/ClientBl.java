package main.java.businesslogic.clientbl;

import main.java.businesslogicservice.clientblservice.ClientBlService;
import main.java.po.client.ClientPO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;

import java.util.ArrayList;

public class ClientBl implements ClientBlService,ClientTool{
    /**
     * @version: 1
     * @date:
     * @param: [query] 包含待查询信息的客户查询对象
     * @function: 将ClientQueryVO转为ClientQueryPO，调用ClientDatdaService.find服务
     *             得到ArrayList<ClientPO>以后转成ArrayList<ClientVO>，返回ArrayList<ClientVO>
     */
    @Override
    public ArrayList<ClientVO> getClientList(ClientQueryVO query) {

        return null;
    }

    /**
     * @version: 1
     * @date:
     * @param: [client] 增加的的客户对象，用于增加数据库中该客户数据
     * @function: 将ClientVO转成ClientPO，并调用ClientDataService.insert服务，返回ResultMessage
     */
    @Override
    public String addClient(ClientVO client) {
        String id="";

        ClientPO clientPO=new ClientPO();


        return id;
    }

    /**
     * @version: 1
     * @date:
     * @param: [client] 修改的的客户对象，用于修改数据库中该客户数据
     * @function: 将ClientVO转成ClientPO，并调用ClientDataService.update服务，返回ResultMessage
     */
    @Override
    public void editClient(ClientVO client) {
        ClientPO clientPO=new ClientPO();


    }

    /**
     * @version: 1
     * @date:
     * @param: [client] 删除的的客户对象，用于删除数据库中该客户数据
     * @function: 将ClientVO转成ClientPO，并调用ClientDataService.delete服务，返回ResultMessage
     */
    @Override
    public void deleteClient(String clientID) {
        ClientPO clientPO=new ClientPO();


    }
}
