package stub_driver.Server.test.java;

import businesslogic.blutility.ResultMessage;
import dataservice.financedataservice.PaymentBillDataService;
import org.junit.Test;
import po.PaymentBillPO;
import stub_driver.Server.main.java.PaymentBillDataServiceStub;

import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class PaymentBillDataServiceDriver {

    PaymentBillDataService paymentBillDataService = new PaymentBillDataServiceStub();
    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        PaymentBillPO paymentBillPO = new PaymentBillPO("FKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillDataService.submitDoc(paymentBillPO), ResultMessage.success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        PaymentBillPO paymentBillPO = new PaymentBillPO("FKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillDataService.saveDoc(paymentBillPO), ResultMessage.success);
    }

    @Test
    public void find() throws Exception {
        assertEquals(paymentBillDataService.find("FKD-2017-10-22-12345").getComment(),"");
    }

    @Test
    public void insert() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        PaymentBillPO paymentBillPO = new PaymentBillPO("FKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillDataService.insert(paymentBillPO),ResultMessage.success);
    }

    @Test
    public void update() throws Exception {
        HashMap map = new HashMap<String, Double>();
        map.put("6212262402017123456",10000);
        PaymentBillPO paymentBillPO = new PaymentBillPO("FKD-2017-10-22-12345",0,new Date(),1000,"finance","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillDataService.update(paymentBillPO),ResultMessage.success);
    }

    @Test
    public void init() throws Exception {
        assertEquals(paymentBillDataService.init(),ResultMessage.success);
    }

    @Test
    public void finish() throws Exception {
        assertEquals(paymentBillDataService.finish(),ResultMessage.success);
    }

}