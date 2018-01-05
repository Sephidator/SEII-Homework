package main.java.client_blservicestub.saleblservicestub;

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

public class SaleTradeBillBlServiceStub implements SaleTradeBillBlService {

    @Override
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public String submit(SaleTradeBillVO saleTradeBillVO) throws Exception {
        return "";
    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public void editSaleTradeBill(SaleTradeBillVO saleTradeBillVO) throws Exception {

    }
}
