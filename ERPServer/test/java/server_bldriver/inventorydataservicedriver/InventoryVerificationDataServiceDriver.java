package server_bldriver.inventorydataservicedriver;

import dataservice.inventorydataservice.InventoryVerificationDataService;
import org.junit.Test;
import po.RepositoryPO;
import server_dataservicestub.inventorydataservicestub.InventoryVerificationDataServiceStub;

import static org.junit.Assert.*;

public class InventoryVerificationDataServiceDriver {

    RepositoryPO repositoryPO=new RepositoryPO(null,null,null,100,200,
            100.5,200.5,50);

    InventoryVerificationDataService inventoryVerificationDataService=new InventoryVerificationDataServiceStub();

    @Test
    public void findView() throws Exception {
        assertEquals(100,inventoryVerificationDataService.findView(null).getOutNumber());
    }

}