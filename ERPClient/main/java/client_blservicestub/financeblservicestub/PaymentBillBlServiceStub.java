package client_blservicestub.financeblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.CashBillBlService;
import businesslogicservice.financeblservice.PaymentBillBlService;
import po.UserPO;
import vo.PaymentBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wangn on 2017.10.21.
 */
public class PaymentBillBlServiceStub implements PaymentBillBlService{


    @Override
    public String getPaymentBillID(PaymentBillVO vo) {
        return "FKD-2017-10-22-12345";
    }

    @Override
    public ResultMessage newPaymentBill(String DocID, ArrayList<String> client, ArrayList<String> transList, String total, String operator) {
        return ResultMessage.success;
    }

    @Override
    public PaymentBillVO showPaymentBill(String DocID) {
        HashMap map = new HashMap<String,Double>();
        map.put("6212262402017123456",10000);
        PaymentBillVO paymentBillVO = new PaymentBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        return paymentBillVO;
    }

    @Override
    public ResultMessage mockPaymentBill(String DocID, ArrayList<String> client, ArrayList<String> transList, String total, String operator) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage mockPaymentBillStatus(String DocID) {
        return ResultMessage.success;
    }

    @Override
    public double getPaymentBillTotal(ArrayList<Double> list) {
        double total = 0;
        for(int i = 0; i < list.size(); i++)
            total += list.get(i);
        return total;
    }

    @Override
    public UserPO getOperator() {
        return new UserPO(0, 45, true,"wang","finance","1234",new  String[]{});
    }


    @Override
    public ResultMessage reversePaymentBill(PaymentBillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage showButton(String DocID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage submitDoc(PaymentBillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDoc(PaymentBillVO vo) {
        return ResultMessage.success;
    }
}
