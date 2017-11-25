package main.java.businesslogic.promotionbl;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionBl implements PromotionBlService, PromotionTool {
    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        return null;
    }

    @Override
    public ResultMessage addPromotion(PromotionVO vo) {
        return null;
    }

    @Override
    public ResultMessage editPromotion(PromotionVO vo) {
        return null;
    }

    @Override
    public ResultMessage deletePromotion(PromotionVO vo) {
        return null;
    }

    @Override
    public String getID() {
        return null;
    }
}
