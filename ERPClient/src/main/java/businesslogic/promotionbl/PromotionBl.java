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
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query)throws Exception {
        return null;
    }

    /**
     * @version: 1
     * @date: 2017.12.08 14:51
     * @para: [promotionID]
     * @function:
     */
    @Override
    public PromotionVO find(String promotionID) {
        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public String addPromotion(PromotionVO vo) throws Exception{

        return null;
    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void editPromotion(PromotionVO vo)throws Exception {

    }

    @Override
    /**
     * @version: 1
     * @date: 2017.11.28 2:09
     * @para: [vo] 
     * @function: 
     */
    public void deletePromotion(PromotionVO vo) throws Exception{

    }


}
