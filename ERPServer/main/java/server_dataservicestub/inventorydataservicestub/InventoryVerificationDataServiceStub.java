package server_dataservicestub.inventorydataservicestub;

import dataservice.inventorydataservice.InventoryVerificationDataService;
import po.RepositoryPO;

import java.rmi.RemoteException;

public class InventoryVerificationDataServiceStub implements InventoryVerificationDataService {

    RepositoryPO repositoryPO=new RepositoryPO(null,null,null,100,200,
            100.5,200.5,50);

    @Override
    public RepositoryPO findView(String date) throws RemoteException {
        return repositoryPO;
    }
}
