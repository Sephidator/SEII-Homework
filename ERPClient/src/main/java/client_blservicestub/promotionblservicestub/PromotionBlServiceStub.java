package main.java.client_blservicestub.promotionblservicestub;

import main.java.businesslogic.blutility.ResultMessage;
import main.java.businesslogicservice.promotionblservice.PromotionBlService;
import main.java.vo.promotion.PromotionQueryVO;
import main.java.vo.promotion.PromotionVO;

import java.util.ArrayList;

public class PromotionBlServiceStub implements PromotionBlService {
    @Override
    public ArrayList<PromotionVO> getPromotionList(PromotionQueryVO query) {
        ArrayList<PromotionVO> list = new ArrayList<>();
        list.add(new PromotionVO());
        list.add(new PromotionVO());
        list.add(new PromotionVO());
        return list;
    }

    @Override
    public ResultMessage addPromotion(PromotionVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage editPromotion(PromotionVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage deletePromotion(PromotionVO vo) {
        return ResultMessage.success;
    }

    @Override
    public String getID() {
        return "12345";
    }
}
