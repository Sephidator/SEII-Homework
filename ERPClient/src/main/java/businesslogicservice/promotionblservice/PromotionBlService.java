package main.java.businesslogicservice.promotionblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.goods.GoodsQueryVO;
import main.java.vo.goods.GoodsVO;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionBlService {
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception;

    public String addPromotion(PromotionVO vo) throws Exception;

    public void editPromotion(PromotionVO vo) throws Exception;

    public void deletePromotion(String promotionID) throws Exception;

    //目的是总经理制定促销策略的时候可以选择用于赠送的商品列表
    public ArrayList<GoodsVO> getGoodsList(GoodsQueryVO query)throws Exception;
}
