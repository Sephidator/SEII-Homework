package main.java.businesslogicservice.saleblservice;

import main.java.vo.bill.BillQueryVO;
import main.java.vo.bill.salebill.SaleTradeBillVO;
import main.java.vo.client.ClientQueryVO;
import main.java.vo.client.ClientVO;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface SaleTradeBillBlService {

    ArrayList<ClientVO> getSellerList(ClientQueryVO query) throws Exception;

    ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query) throws Exception;

    ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception;

    String submit(SaleTradeBillVO bill) throws Exception;

    //public void saveDraft(SaleTradeBillVO bill) throws Exception;

    ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query) throws Exception;

    void editSaleTradeBill(SaleTradeBillVO saleTradeBillVO) throws Exception;
}
