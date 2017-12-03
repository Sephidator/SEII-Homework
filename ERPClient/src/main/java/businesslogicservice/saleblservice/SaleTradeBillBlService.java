package main.java.businesslogicservice.saleblservice;

import main.java.businesslogic.blutility.ResultMessage;
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

    public ArrayList<ClientVO> getSellerList(ClientQueryVO query);

    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query);

    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query);

    public String submit(SaleTradeBillVO bill);

    public void saveDraft(SaleTradeBillVO bill);

    public ArrayList<SaleTradeBillVO> getSaleTradeBillList(BillQueryVO query);

}
