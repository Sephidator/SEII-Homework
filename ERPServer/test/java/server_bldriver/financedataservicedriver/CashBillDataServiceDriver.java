package stub_driver.Server.test.java;

import businesslogic.blutility.ResultMessage;
import dataservice.financedataservice.CashBillDataService;
import org.junit.Test;
import po.CashBillPO;
import stub_driver.Server.main.java.CashBillDataServiceStub;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class CashBillDataServiceDriver {

    CashBillDataService cashBillDataService = new CashBillDataServiceStub();
    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("lampX",10000);
        CashBillPO cashBillPO = new CashBillPO("XJFYD-2017-10-21-111222",0,new Date(),1000,"finance","",map);
        assertEquals(cashBillDataService.submitDoc(cashBillPO), ResultMessage.success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("lampX",10000);
        CashBillPO cashBillPO = new CashBillPO("XJFYD-2017-10-21-111222",0,new Date(),1000,"finance","",map);
        assertEquals(cashBillDataService.saveDoc(cashBillPO), ResultMessage.success);
    }

    @Test
    public void find() throws Exception {
        assertEquals(cashBillDataService.find("XJFYD-2017-10-21-111222").getComment(),"");
    }

    @Test
    public void insert() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("lampX",10000);
        CashBillPO cashBillPO = new CashBillPO("XJFYD-2017-10-21-111222",0,new Date(),1000,"finance","",map);
        assertEquals(cashBillDataService.insert(cashBillPO), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("lampX",10000);
        CashBillPO cashBillPO = new CashBillPO("XJFYD-2017-10-21-111222",0,new Date(),1000,"finance","",map);
        assertEquals(cashBillDataService.update(cashBillPO), ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(cashBillDataService.init(),ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(cashBillDataService.finish(),ResultMessage.success);
    }

}