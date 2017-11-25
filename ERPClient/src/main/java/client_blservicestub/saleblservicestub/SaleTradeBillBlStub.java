package main.java.client_blservicestub.saleblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

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
