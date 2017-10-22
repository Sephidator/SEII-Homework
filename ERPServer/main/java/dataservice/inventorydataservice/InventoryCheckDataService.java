package dataservice.inventorydataservice;

import po.RepositoryPO;
import data.datautility.ResultMessage;
import java.rmi.RemoteException;

public interface InventoryCheckDataService {
    public RepositoryPO findView(String beginDate, String endDate) throws RemoteException;
}
