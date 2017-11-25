package main.java.businesslogic_mock.purchaseblmock;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public class PurchaseTradeBillToolMock implements PurchaseTradeBillTool{
    @Override
    public ResultMessage pass(PurchaseTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage reject(PurchaseTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) {
        ArrayList<PurchaseTradeBillVO> list=new ArrayList<>();
        list.add(new PurchaseTradeBillVO());
        return list;
    }
}
