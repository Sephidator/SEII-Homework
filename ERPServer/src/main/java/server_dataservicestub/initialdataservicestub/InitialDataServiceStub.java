package main.java.server_dataservicestub.initialdataservicestub;

import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InitialDataServiceStub implements InitialDataService {

    @Override
    public ArrayList<InitialPO> find(InitialQueryPO query) throws RemoteException {
        ArrayList<InitialPO> list = new ArrayList<>();
        list.add(new InitialPO());
        return list;
    }

    @Override
    public String insert(InitialPO po) throws RemoteException {
        return "00000001";
    }

}
