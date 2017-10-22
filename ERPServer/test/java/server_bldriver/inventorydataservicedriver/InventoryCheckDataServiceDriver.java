package server_bldriver.inventorydataservicedriver;

import dataservice.inventorydataservice.InventoryCheckDataService;
import org.junit.Test;
import po.RepositoryPO;
import server_dataservicestub.inventorydataservicestub.InventoryCheckDataServiceStub;

import static org.junit.Assert.*;

public class InventoryCheckDataServiceDriver {

    RepositoryPO repositoryPO=new RepositoryPO(null,null,null,100,200,
            100.5,200.5,50);
    InventoryCheckDataService inventoryCheckDataService=new InventoryCheckDataServiceStub();


    @Test
    public void findView() throws Exception {
        assertEquals(100,inventoryCheckDataService.findView(null,null).getOutNumber());
    }

}