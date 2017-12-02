package main.java.server_dataservicestub.initialdataservicestub;

import main.java.data.datautility.ResultMessage;
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
    public ResultMessage insert(InitialPO po) throws RemoteException {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getInitialID() throws RemoteException {
        return "Initial00000004";
    }
}
