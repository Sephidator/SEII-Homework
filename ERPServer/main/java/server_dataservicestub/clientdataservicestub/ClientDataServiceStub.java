package server_dataservicestub.clientdataservicestub;

import data.datautility.ResultMessage;
import dataservice.clientdataservice.ClientDataService;
import po.ClientPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ClientDataServiceStub implements ClientDataService{

    @Override
    public ResultMessage insert(ClientPO clientpo) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<ClientPO> find(String clientInfo) throws RemoteException {
        ClientPO client1=new ClientPO("123", "销售商", 3, "刘钦",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", 100, 20, 100,"12345");
        ClientPO client2=new ClientPO("233", "供应商", 3, "宋抟",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", 100, 20, 100,"23456");

        ArrayList<ClientPO> result=new ArrayList<>();
        result.add(client1);
        result.add(client2);

        return result;
    }

    @Override
    public ResultMessage delete(ClientPO clientpo) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(ClientPO clientpo) throws RemoteException {
        return ResultMessage.success;
    }
}
