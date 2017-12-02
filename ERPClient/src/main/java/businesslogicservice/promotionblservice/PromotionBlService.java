package main.java.businesslogicservice.promotionblservice;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionBlService {
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query);

    public void addPromotion(PromotionVO vo);

    public void editPromotion(PromotionVO vo);

    public void deletePromotion(PromotionVO vo);
}
