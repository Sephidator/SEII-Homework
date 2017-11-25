package main.java.businesslogic_mock.promotionblmock;

import main.java.businesslogic.promotionbl.PromotionTool;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionToolMock implements PromotionTool {
    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        ArrayList<PromotionVO> list = new ArrayList<>();
        list.add(new PromotionVO());
        return list;
    }
}
