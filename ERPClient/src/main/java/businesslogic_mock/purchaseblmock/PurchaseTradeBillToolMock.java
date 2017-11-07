package businesslogic_mock.purchaseblmock;

import businesslogic.blutility.ResultMessage;
import businesslogic.purchasebl.PurchaseTradeBillTool;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;

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
