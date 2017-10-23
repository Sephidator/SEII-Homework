package client_blservicestub.financeblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.financeblservice.CashBillBlService;
import po.UserPO;
import vo.CashBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wangn on 2017.10.21.
 */
public class CashBillBlServiceStub implements CashBillBlService{


    @Override
    public String getCashBillID(CashBillVO vo) {
        return "XJFYD-2017-10-22-123456";
    }

    @Override
    public ResultMessage newCashBill(String DocID, ArrayList<String> bankAccount, ArrayList<String> itemList, String total, String operator) {
        return ResultMessage.success;
    }

    @Override
    public CashBillVO showCashBill(String DocID) {
        HashMap map = new HashMap<String,Double>();
        map.put("lamp",1000);
        CashBillVO cashBillVO = new CashBillVO("XJFYD-2017-10-22-123456",0, new Date(), 0,"wang","",map);
        return cashBillVO;
    }

    @Override
    public ResultMessage mockCashBill(String DocID, ArrayList<String> bankAccount, ArrayList<String> itemList, String total, String operator) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage mockCashBillStatus(String DocID) {
        return ResultMessage.success;
    }

    @Override
    public double getCashBillTotal(ArrayList<Double> list) {
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
    public ResultMessage reverseCashBill(CashBillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage showButton(String DocID) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage submitDoc(CashBillVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDoc(CashBillVO vo) {
        return ResultMessage.success;
    }
}