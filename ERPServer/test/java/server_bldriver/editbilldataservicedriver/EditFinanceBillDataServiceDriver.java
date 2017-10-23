package server_bldriver.editbilldataservicedriver;

import dataservice.editbilldataservice.EditFinanceBillDataService;
import org.junit.Test;
import server_dataservicestub.editbilldataservicestub.EditFinanceBillDataServiceStub;

import static org.junit.Assert.*;

public class EditFinanceBillDataServiceDriver {
    EditFinanceBillDataService service=new EditFinanceBillDataServiceStub();

    @Test
    public void getFinanceBill() throws Exception {
        assertEquals(2,service.getFinanceBill(0).size());
    }

}