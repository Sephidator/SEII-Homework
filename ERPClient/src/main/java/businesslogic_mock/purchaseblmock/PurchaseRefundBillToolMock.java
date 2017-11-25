package main.java.businesslogic_mock.purchaseblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;

import java.util.ArrayList;

public class PurchaseRefundBillToolMock implements PurchaseRefundBillTool{
    @Override
    public ResultMessage pass(PurchaseRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(PurchaseRefundBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) {
        ArrayList<PurchaseRefundBillVO> list=new ArrayList<>();
        list.add(new PurchaseRefundBillVO());
        return list;
    }
}
