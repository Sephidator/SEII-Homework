package main.java.businesslogic.promotionbl;

import main.java.businesslogic.blutility.ResultMessage;
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
    public ResultMessage addPromotion(PromotionVO vo) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public ResultMessage editPromotion(PromotionVO vo) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public ResultMessage deletePromotion(PromotionVO vo) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [] 
     * @function: 
     */
    public String getID() {
        return null;
    }
}
