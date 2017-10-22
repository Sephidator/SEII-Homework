package client_blservicestub.financeblservicestub;

import businesslogicservice.financeblservice.ReceiptBillBlService;
import po.UserPO;
import vo.ReceiptBillVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wangn on 2017.10.21.
 */
public class ReceiptBillBlServiceStub implements ReceiptBillBlService{

    @Override
    public String getReceiptBillID(ReceiptBillVO vo) {
        return "SKD-2017-10-22-12345";
    }

    @Override
    public ResultMessage newReceiptBill(String DocID, ArrayList<String> client, ArrayList<String> transList, String total, String operator) {
        return ResultMessage.Success;
    }

    @Override
    public ReceiptBillVO showReceiptBill(String DocID) {
        HashMap map = new HashMap<String,Double>();
        map.put("6212262402017123456",10000);
        ReceiptBillVO receiptBillVO = new ReceiptBillVO("FKD-2017-10-22-12345",0, new Date(), 0,"wang","","经销商：张三，供应商：李四",map);
        return receiptBillVO;
    }

    @Override
    public ResultMessage mockReceiptBill(String DocID, ArrayList<String> client, ArrayList<String> transList, String total, String operator) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage mockReceiptBillStatus(String DocID) {
        return ResultMessage.Success;
    }

    @Override
    public double getReceiptBillTotal(ArrayList<Double> list) {
        double total = 0;
        for(int i = 0; i < list.size(); i++)
            total += list.get(i);
        return total;
    }

    @Override
    public UserPO getOperator()  {
        return new UserPO(0, 45, true,"wang","finance","1234",new  String[]{});
    }

    @Override
    public ResultMessage reverseReceiptBill(ReceiptBillVO vo) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage showButton(String DocID) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage submitDoc(ReceiptBillVO vo) {
        return ResultMessage.Success;
    }

    @Override
    public ResultMessage saveDoc(ReceiptBillVO vo) {
        return ResultMessage.Success;
    }
}