package main.java.businesslogic.promotionbl;

import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public interface PromotionTool {
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query);
}
