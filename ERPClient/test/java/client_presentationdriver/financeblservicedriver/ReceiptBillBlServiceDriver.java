package stub_driver.Client.test.java;

import businesslogicservice.financeblservice.ReceiptBillBlService;
import org.junit.Test;
import stub_driver.Client.main.java.ReceiptBillBlServiceStub;
import vo.ReceiptBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class ReceiptBillBlServiceDriver {

    ReceiptBillBlService receiptBillBlService = new ReceiptBillBlServiceStub();
    @Test
    public void getReceiptBillID() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO receiptBillVO = new ReceiptBillVO("SKD-2017-10-22-12345",0, new Date(), 0,"wang","","�����̣���������Ӧ�̣�����",map);
        assertEquals(receiptBillBlService.getReceiptBillID(receiptBillVO),"SKD-2017-10-22-12345");
    }

    @Test
    public void newReceiptBill() throws Exception {
        assertEquals(receiptBillBlService.newReceiptBill("SKD-2017-10-21-12345",new ArrayList<String>(){},new ArrayList<String>(){},"0","finance"), ReceiptBillBlService.ResultMessage.Success);

    }

    @Test
    public void showReceiptBill() throws Exception {
        assertEquals(receiptBillBlService.showReceiptBill("SKD-2017-10-21-12345").getClient(),"�����̣���������Ӧ�̣�����");
    }

    @Test
    public void mockReceiptBill() throws Exception {
        assertEquals(receiptBillBlService.mockReceiptBillStatus("SKD-2017-10-21-12345"), ReceiptBillBlService.ResultMessage.Success);
    }

    @Test
    public void mockReceiptBillStatus() throws Exception {
        assertEquals(receiptBillBlService.mockReceiptBillStatus("SKD-2017-10-21-12345"), ReceiptBillBlService.ResultMessage.Success);

    }

    @Test
    public void getReceiptBillTotal() throws Exception {
        assertEquals(receiptBillBlService.getReceiptBillTotal(new ArrayList<Double>()),0);
    }

    @Test
    public void getOperator() throws Exception {
        assertEquals(receiptBillBlService.getOperator().getAge(),45);
    }

    @Test
    public void reverseReceiptBill() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO paymentBillVO = new ReceiptBillVO("SKD-2017-10-22-12345",0, new Date(), 0,"wang","","�����̣���������Ӧ�̣�����",map);
        assertEquals(receiptBillBlService.reverseReceiptBill(paymentBillVO), ReceiptBillBlService.ResultMessage.Success);
    }

    @Test
    public void showButton() throws Exception {
        assertEquals(receiptBillBlService.showButton(""), ReceiptBillBlService.ResultMessage.Success);
    }

    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO paymentBillVO = new ReceiptBillVO("SKD-2017-10-22-12345",0, new Date(), 0,"wang","","�����̣���������Ӧ�̣�����",map);
        assertEquals(receiptBillBlService.submitDoc(paymentBillVO), ReceiptBillBlService.ResultMessage.Success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO paymentBillVO = new ReceiptBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","�����̣���������Ӧ�̣�����",map);
        assertEquals(receiptBillBlService.saveDoc(paymentBillVO), ReceiptBillBlService.ResultMessage.Success);
    }

}