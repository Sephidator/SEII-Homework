package client_presentationdriver.financeblservicedriver;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.ReceiptBillBlService;
import client_blservicestub.financeblservicestub.ReceiptBillBlServiceStub;
import org.junit.Test;
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
        ReceiptBillVO receiptBillVO = new ReceiptBillVO("SKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillBlService.getReceiptBillID(receiptBillVO),"SKD-2017-10-22-12345");
    }

    @Test
    public void newReceiptBill() throws Exception {
        assertEquals(receiptBillBlService.newReceiptBill("SKD-2017-10-21-12345",new ArrayList<String>(){},new ArrayList<String>(){},"0","finance"), ResultMessage.success);

    }

    @Test
    public void showReceiptBill() throws Exception {
        assertEquals(receiptBillBlService.showReceiptBill("SKD-2017-10-21-12345").getClient(),"经销商：张三，供应商：李四");
    }

    @Test
    public void mockReceiptBill() throws Exception {
        assertEquals(receiptBillBlService.mockReceiptBillStatus("SKD-2017-10-21-12345"), ResultMessage.success);
    }

    @Test
    public void mockReceiptBillStatus() throws Exception {
        assertEquals(receiptBillBlService.mockReceiptBillStatus("SKD-2017-10-21-12345"), ResultMessage.success);

    }

    @Test
    public void getReceiptBillTotal() throws Exception {
        assertEquals(receiptBillBlService.getReceiptBillTotal(new ArrayList<Double>()),0,0.1);
    }

    @Test
    public void getOperator() throws Exception {
        assertEquals(receiptBillBlService.getOperator().getAge(),45);
    }

    @Test
    public void reverseReceiptBill() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO paymentBillVO = new ReceiptBillVO("SKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillBlService.reverseReceiptBill(paymentBillVO), ResultMessage.success);
    }

    @Test
    public void showButton() throws Exception {
        assertEquals(receiptBillBlService.showButton(""), ResultMessage.success);
    }

    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO paymentBillVO = new ReceiptBillVO("SKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillBlService.submitDoc(paymentBillVO), ResultMessage.success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        ReceiptBillVO paymentBillVO = new ReceiptBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(receiptBillBlService.saveDoc(paymentBillVO), ResultMessage.success);
    }

}