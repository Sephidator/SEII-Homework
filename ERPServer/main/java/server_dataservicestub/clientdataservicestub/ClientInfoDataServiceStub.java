package server_dataservicestub.clientdataservicestub;

import dataservice.clientdataservice.ClientInfoDataService;
import po.ClientPO;

import java.rmi.RemoteException;

public class ClientInfoDataServiceStub implements ClientInfoDataService{

    @Override
    public ClientPO getClient(String ID) throws RemoteException {
        ClientPO client1=new ClientPO("123", "销售商", 3, "刘钦",
                "13219068745", "南京大学", "215023",
                "liuqin@smail.nju.edu.cn", 100, 20, 100,"12345");
        return client1;
    }
}
