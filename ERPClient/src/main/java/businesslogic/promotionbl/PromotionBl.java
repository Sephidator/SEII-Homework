package main.java.businesslogic.promotionbl;

import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionBl implements PromotionBlService, PromotionTool {
    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [query] 
     * @function: 
     */
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void addPromotion(PromotionVO vo) {

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void editPromotion(PromotionVO vo) {

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void deletePromotion(PromotionVO vo) {

    }


}
