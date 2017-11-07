package businesslogic.salebl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.saleblservice.SaleTradeBillBlService;
import vo.bill.BillQueryVO;
import vo.bill.salebill.SaleTradeBillVO;
import vo.client.ClientQueryVO;
import vo.client.ClientVO;
import vo.goods.GoodsQueryVO;
import vo.goods.GoodsVO;
import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;

public class SaleTradBillBl implements SaleTradeBillBlService,SaleTradeBillTool {
    @Override
    public String getID() {
        return null;
    }

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        return null;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage submit(SaleTradeBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage saveDraft(SaleTradeBillVO bill) {
        return null;
    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage pass(SaleTradeBillVO bill) {
        return null;
    }

    @Override
    public ResultMessage reject(SaleTradeBillVO bill) {
        return null;
    }
}
