package businesslogic.purchasebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.purchaseblservice.PurchaseTradeBillBlService;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public class PurchaseTradeBillBl implements PurchaseTradeBillBlService,PurchaseTradeBillTool {
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
    public ResultMessage submit(PurchaseTradeBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(PurchaseTradeBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(PurchaseTradeBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(PurchaseTradeBillVO bill) {
        return null;
    }
}
