package businesslogic.promotionbl;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.promotionblservice.PromotionBlService;
import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

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
