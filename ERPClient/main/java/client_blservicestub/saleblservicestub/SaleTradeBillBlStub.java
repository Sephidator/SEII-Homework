package client_blservicestub.saleblservicestub;

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

public class SaleTradeBillBlStub implements SaleTradeBillBlService{
    @Override
    public String getID() {
        return "123";
    }

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) {
        ArrayList<ClientVO> list=new ArrayList<>();
        list.add(new ClientVO());
        return list;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) {
        ArrayList<GoodsVO> list=new ArrayList<>();
        list.add(new GoodsVO());
        return list;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        ArrayList<PromotionVO> list=new ArrayList<>();
        list.add(new PromotionVO());
        return list;
    }

    @Override
    public ResultMessage submit(SaleTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDraft(SaleTradeBillVO bill) {
        return ResultMessage.success;
    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) {
        ArrayList<SaleTradeBillVO> list=new ArrayList<>();
        list.add(new SaleTradeBillVO());
        return list;
    }
}
