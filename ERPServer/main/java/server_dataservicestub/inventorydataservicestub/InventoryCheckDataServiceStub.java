package server_dataservicestub.inventorydataservicestub;

import dataservice.inventorydataservice.InventoryCheckDataService;
import po.RepositoryPO;

import java.rmi.RemoteException;

public class InventoryCheckDataServiceStub implements InventoryCheckDataService {

    RepositoryPO repositoryPO=new RepositoryPO(null,null,null,100,200,
            100.5,200.5,50);

    @Override
    public RepositoryPO findView(String beginDate, String endDate) throws RemoteException {
        return repositoryPO;
    }
}
