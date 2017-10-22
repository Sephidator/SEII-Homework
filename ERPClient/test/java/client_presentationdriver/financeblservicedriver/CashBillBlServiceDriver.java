package stub_driver.Client.test.java;

import businesslogicservice.financeblservice.CashBillBlService;
import org.junit.Test;
import stub_driver.Client.main.java.CashBillBlServiceStub;
import vo.CashBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by wangn on 2017.10.22.
 */
public class CashBillBlServiceDriver {

    CashBillBlService cashBillBlService = new CashBillBlServiceStub();
    @Test
    public void getCashBillID() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        CashBillVO cashBillVO = new CashBillVO("XJFYD-2017-10-22-12345",0, new Date(), 0,"wang","",map);
        assertEquals(cashBillBlService.getCashBillID(cashBillVO),"XJFYD-2017-10-22-12345");
    }

    @Test
    public void newCashBill() throws Exception {
        assertEquals(cashBillBlService.newCashBill("XJFYD-2017-10-21-12345",new ArrayList<String>(){},new ArrayList<String>(){},"0","finance"), CashBillBlService.ResultMessage.Success);
    }

    @Test
    public void showCashBill() throws Exception {
        assertEquals(cashBillBlService.showCashBill("XJFYD-2017-10-21-12345").getID(),"XJFYD-2017-10-21-12345");
    }

    @Test
    public void mockCashBill() throws Exception {
        assertEquals(cashBillBlService.mockCashBill("XJFYD-2017-10-21-12345",new ArrayList<String>(){},new ArrayList<String>(){},"0","finance"), CashBillBlService.ResultMessage.Success);
    }

    @Test
    public void mockCashBillStatus() throws Exception {
        assertEquals(cashBillBlService.mockCashBillStatus("XJFYD-2017-10-21-12345"), CashBillBlService.ResultMessage.Success);
    }

    @Test
    public void getCashBillTotal() throws Exception {
        ArrayList<Double> list = new ArrayList<Double>();
        list.add(0.0);
        assertEquals(cashBillBlService.getCashBillTotal(list),0.0);
    }

    @Test
    public void getOperator() throws Exception {
        assertEquals(cashBillBlService.getOperator().getAge(),45);
    }

    @Test
    public void reverseCashBill() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        CashBillVO cashBillVO = new CashBillVO("XJFYD-2017-10-22-12345",0, new Date(), 0,"wang","",map);
        assertEquals(cashBillBlService.reverseCashBill(cashBillVO), CashBillBlService.ResultMessage.Success);
    }

    @Test
    public void showButton() throws Exception {
        assertEquals(cashBillBlService.showButton("XJFYD-2017-10-22-12345"), CashBillBlService.ResultMessage.Success);
    }

    @Test
    public void submitDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        CashBillVO cashBillVO = new CashBillVO("XJFYD-2017-10-22-12345",0, new Date(), 0,"wang","",map);
        assertEquals(cashBillBlService.submitDoc(cashBillVO), CashBillBlService.ResultMessage.Success);
    }

    @Test
    public void saveDoc() throws Exception {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        CashBillVO cashBillVO = new CashBillVO("XJFYD-2017-10-22-12345",0, new Date(), 0,"wang","",map);
        assertEquals(cashBillBlService.saveDoc(cashBillVO), CashBillBlService.ResultMessage.Success);
    }

}