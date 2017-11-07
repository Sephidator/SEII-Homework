package businesslogic_mock.purchaseblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.purchasebl.PurchaseRefundBillTool;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseRefundBillVO;

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
