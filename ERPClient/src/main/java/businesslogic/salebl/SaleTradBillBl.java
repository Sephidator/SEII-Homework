package main.java.businesslogic.salebl;

import main.java.businesslogicservice.saleblservice.SaleTradeBillBlService;
import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.BillVO;
import main.java.vo.bill.salebill.SaleTradeBillQueryVO;
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
    public ArrayList<ClientVO> getSellerList(ClientQueryVO query)throws Exception {
        return null;
    }

    @Override
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query)throws Exception {
        return null;
    }

    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query)throws Exception {
        return null;
    }

    @Override
    public String submit(SaleTradeBillVO bill)throws Exception {
        return null;
    }

    @Override
    public void saveDraft(SaleTradeBillVO bill) throws Exception{

    }

    @Override
    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query)throws Exception {
        return null;
    }

    @Override
    public ArrayList<SaleTradeBillVO> findsByReport(SaleTradeBillQueryVO query) throws Exception {
        return null;
    }

    @Override
    public void pass(BillVO billVO) throws Exception{

    }

    @Override
    public void reject(BillVO billVO) throws Exception{

    }
}
