package main.java.businesslogicservice.promotionblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionBlService {
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception;

    public String addPromotion(PromotionVO vo) throws Exception;

    public void editPromotion(PromotionVO vo) throws Exception;

    public void deletePromotion(PromotionVO vo) throws Exception;
}
