package stub_driver.Server.test.java;

import businesslogic.blutility.ResultMessage;
import dataservice.financedataservice.ReceiptBillDataService;
import org.junit.Test;
import po.ReceiptBillPO;
import stub_driver.Server.main.java.ReceiptBillDataServiceStub;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class ReceiptBillDataServiceDriver {

    ReceiptBillDataService receiptBillDataService = new ReceiptBillDataServiceStub();
    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        ReceiptBillPO receiptBillPO = new ReceiptBillPO("SKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillDataService.submitDoc(receiptBillPO), ResultMessage.success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        ReceiptBillPO receiptBillPO = new ReceiptBillPO("SKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillDataService.saveDoc(receiptBillPO), ResultMessage.success);
    }

    @Test
    public void find() throws Exception {
        assertEquals(receiptBillDataService.find("SKD-2017-10-22-12345").getComment(),"");
    }

    @Test
    public void insert() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        ReceiptBillPO receiptBillPO = new ReceiptBillPO("SKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillDataService.insert(receiptBillPO), ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        ReceiptBillPO receiptBillPO = new ReceiptBillPO("SKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillDataService.update(receiptBillPO), ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(receiptBillDataService.init(),ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(receiptBillDataService.init(),ResultMessage.success);
    }

}