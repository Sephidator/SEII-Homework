package client_presentationdriver.financeblservicedriver;

import businesslogicservice.financeblservice.PaymentBillBlService;
import client_blservicestub.financeblservicestub.PaymentBillBlServiceStub;
import org.junit.Test;
import vo.PaymentBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class PaymentBillBlServiceDriver {

    PaymentBillBlService paymentBillBlService = new PaymentBillBlServiceStub();
    @Test
    public void getPaymentBillID() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        PaymentBillVO paymentBillVO = new PaymentBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillBlService.getPaymentBillID(paymentBillVO),"FKD-2017-10-22-12345");
    }

    @Test
    public void newPaymentBill() throws Exception {
        assertEquals(paymentBillBlService.newPaymentBill("FKD-2017-10-21-12345",new ArrayList<String>(){},new ArrayList<String>(){},"0","finance"), PaymentBillBlService.ResultMessage.Success);
    }

    @Test
    public void showPaymentBill() throws Exception {
        assertEquals(paymentBillBlService.showPaymentBill("FKD-2017-10-21-12345").getClient(),"经销商：张三，供应商：李四");
    }

    @Test
    public void mockPaymentBill() throws Exception {
        assertEquals(paymentBillBlService.mockPaymentBill("FKD-2017-10-21-12345",new ArrayList<String>(){},new ArrayList<String>(){},"0","finance"), PaymentBillBlService.ResultMessage.Success);
    }

    @Test
    public void mockPaymentBillStatus() throws Exception {
        assertEquals(paymentBillBlService.mockPaymentBillStatus("FKD-2017-10-21-12345"), PaymentBillBlService.ResultMessage.Success);
    }

    @Test
    public void getPaymentBillTotal() throws Exception {
        assertEquals(paymentBillBlService.getPaymentBillTotal(new ArrayList<Double>()),0,0.1);
    }

    @Test
    public void getOperator() throws Exception {
        assertEquals(paymentBillBlService.getOperator().getAge(),45);
    }

    @Test
    public void reversePaymentBill() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        PaymentBillVO paymentBillVO = new PaymentBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillBlService.reversePaymentBill(paymentBillVO), PaymentBillBlService.ResultMessage.Success);
    }

    @Test
    public void showButton() throws Exception {
        assertEquals(paymentBillBlService.showButton(""), PaymentBillBlService.ResultMessage.Success);
    }

    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        PaymentBillVO paymentBillVO = new PaymentBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillBlService.submitDoc(paymentBillVO), PaymentBillBlService.ResultMessage.Success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        PaymentBillVO paymentBillVO = new PaymentBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        assertEquals(paymentBillBlService.saveDoc(paymentBillVO), PaymentBillBlService.ResultMessage.Success);
    }

}