package businesslogic.purchasebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseRefundBillBlService;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseRefundBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseRefundBillBl implements PurchaseRefundBillBlService,PurchaseRefundBillTool {
    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(PurchaseRefundBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(PurchaseRefundBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<PurchaseRefundBillVO> getPurchaseRefundBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(PurchaseRefundBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(PurchaseRefundBillVO bill) {
        return null;
    }

}
