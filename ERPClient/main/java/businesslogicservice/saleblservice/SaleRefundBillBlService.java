package businesslogicservice.saleblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleRefundBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;

import java.util.ArrayList;

public interface SaleRefundBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query);
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);
    public ResultMessage submit(SaleRefundBillVO bill);
    public ResultMessage saveDraft(SaleRefundBillVO bill);
    public ArrayList<SaleRefundBillVO> getSaleRefundBillList(BillQueryVO query);
}
