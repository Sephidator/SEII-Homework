package businesslogic.promotionbl;

import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionTool {
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query);
}
