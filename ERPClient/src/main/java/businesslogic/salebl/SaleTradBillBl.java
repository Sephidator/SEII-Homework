package main.java.businesslogic.salebl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class SaleTradBillBl implements SaleTradeBillBlService,SaleTradeBillTool {


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
    public String submit(SaleTradeBillVO bill) {
        return null;
    }

    @Override
    public void saveDraft(SaleTradeBillVO bill) {

    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) {
        return null;
    }

    @Override
    public void pass(BillVO billVO) {

    }

    @Override
    public void reject(BillVO billVO) {

    }
}
