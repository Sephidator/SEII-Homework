package main.java.businesslogic_mock.purchaseblmock;

import main.java.businesslogic.purchasebl.PurchaseRefundBillTool;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.purchasebill.PurchaseRefundBillVO;

import java.util.ArrayList;

public class PurchaseRefundBillToolMock implements PurchaseRefundBillTool {

    @Override
    public void pass(BillVO billVO) throws Exception {

    }

    @Override
    public void reject(BillVO billVO) throws Exception {

    }

    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }
}
