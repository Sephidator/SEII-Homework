package businesslogicservice.promotionblservice;

import businesslogic.blutility.ResultMessage;
import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionBlService {
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query);

    public ResultMessage addPromotion(PromotionVO vo);

    public ResultMessage editPromotion(PromotionVO vo);

    public ResultMessage deletePromotion(PromotionVO vo);

    public String getID();
}
