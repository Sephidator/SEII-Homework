package main.java.businesslogic_mock.promotionblmock;

import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionToolMock implements PromotionTool {

    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) throws Exception {
        return new ArrayList<>();
    }

    @Override
    public PromotionVO find(String promotionID) throws Exception {
        return new PromotionVO();
    }
}
