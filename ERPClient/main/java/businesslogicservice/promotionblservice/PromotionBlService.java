package businesslogicservice.promotionblservice;

import businesslogic.blutility.ResultMessage;
import vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionBlService {
    ArrayList<PromotionVO> show();

    ResultMessage add(PromotionVO vo);

    ResultMessage update(PromotionVO vo);

    ResultMessage delete(PromotionVO vo);
}
