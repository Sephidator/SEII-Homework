package businesslogicservice.purchaseblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.purchasebill.PurchaseTradeBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface PurchaseTradeBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSupplierList(ClientQueryVO query);
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);
    public ResultMessage submit(PurchaseTradeBillVO bill);
    public ResultMessage saveDraft(PurchaseTradeBillVO bill);
    public ArrayList<PurchaseTradeBillVO> getPurchaseTradeBillList(BillQueryVO query);
}
