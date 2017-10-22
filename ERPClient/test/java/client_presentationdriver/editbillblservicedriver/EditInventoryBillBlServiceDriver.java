package client_presentationdriver.editbillblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.editbillblservice.EditInventoryBillBlService;
import client_blservicestub.editbillblservicestub.EditInventoryBillBlServiceStub;
import org.junit.Test;

import static org.junit.Assert.*;

public class EditInventoryBillBlServiceDriver {

    EditInventoryBillBlService editInventoryBillBlService=new EditInventoryBillBlServiceStub();

    @Test
    public void choose() throws Exception {

        assertEquals(ResultMessage.success,editInventoryBillBlService.choose("YSD-20171022-00002"));
    }

    @Test
    public void getBill() throws Exception {

        assertEquals(ResultMessage.success,editInventoryBillBlService.getBill(1));
    }

}