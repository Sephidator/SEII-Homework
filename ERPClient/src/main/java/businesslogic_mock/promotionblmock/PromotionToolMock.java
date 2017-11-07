package businesslogic_mock.promotionblmock;

import businesslogic.promotionbl.PromotionTool;
import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionToolMock implements PromotionTool {
    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        ArrayList<PromotionVO> list = new ArrayList<>();
        list.add(new PromotionVO());
        return list;
    }
}
