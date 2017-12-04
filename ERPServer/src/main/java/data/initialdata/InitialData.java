package main.java.data.initialdata;

import main.java.dataservice.initialdataservice.InitialDataService;
import main.java.po.initial.InitialPO;
import main.java.po.initial.InitialQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class InitialData implements InitialDataService {
    @Override
    public ArrayList<InitialPO> find(InitialQueryPO query) throws RemoteException {
        return null;
    }

    @Override
    public String insert(InitialPO po) throws RemoteException {
        return null;
    }
}
