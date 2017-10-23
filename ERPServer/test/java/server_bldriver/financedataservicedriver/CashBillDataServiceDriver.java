package server_bldriver.financedataservicedriver;

import data.datautility.ResultMessage;
import dataservice.financedataservice.CashBillDataService;
import org.junit.Test;
import po.CashBillPO;
import server_dataservicestub.financedataservicestub.CashBillDataServiceStub;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

public class CashBillDataServiceDriver {
    CashBillDataService service=new CashBillDataServiceStub();

    @Test
    public void submitDoc() throws Exception {
        assertEquals(ResultMessage.success,service.submitDoc(null));
    }

    @Test
    public void saveDoc() throws Exception {
        assertEquals(ResultMessage.success,service.saveDoc(null));
    }

    @Test
    public void find() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("lampX",10000);
        CashBillPO cashBillPO = new CashBillPO("XJFYD-2017-10-22-12345",0,new Date(),10000,"finance","",map);
        assertEquals(cashBillPO.getID(),service.find("233").getID());
    }

    @Test
    public void insert() throws Exception {
        assertEquals(ResultMessage.success,service.insert(null));
    }

    @Test
    public void update() throws Exception {
        assertEquals(ResultMessage.success,service.update(null));
    }

    @Test
    public void init() throws Exception {
        assertEquals(ResultMessage.success,service.init());
    }

    @Test
    public void finish() throws Exception {
        assertEquals(ResultMessage.success,service.finish());
    }

}