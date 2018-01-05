package main.java.businesslogic_mock.purchaseblmock;

import main.java.businesslogic.purchasebl.PurchaseTradeBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseTradeBillVO;

import java.util.ArrayList;

public class PurchaseTradeBillToolMock implements PurchaseTradeBillTool {

    @Override
    public void pass(BillVO billVO) throws Exception {

    }

    @Override
    public void reject(BillVO billVO) throws Exception {

    }

    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
