package businesslogicservice.saleblservice;

import businesslogic.blutility.ResultMessage;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleTradeBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface SaleTradeBillBlService {
    public String getID ();
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query);
    public ArrayList<ClientVO> getGoodsList(GoodsQueryVO query);
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query);
    public ResultMessage submit(SaleTradeBillVO bill);
    public ResultMessage saveDraft(SaleTradeBillVO bill);
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query);
}
