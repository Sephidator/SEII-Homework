package dataservice.inventorydataservice;

import po.RepositoryPO;
import data.datautility.ResultMessage;
import java.rmi.RemoteException;

public interface InventoryVerificationDataService {

    public RepositoryPO findView(String date) throws RemoteException;
}
