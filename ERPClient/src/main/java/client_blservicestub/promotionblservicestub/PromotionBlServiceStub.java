package client_blservicestub.promotionblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.promotionblservice.PromotionBlService;
import vo.promotion.PromotionQueryVO;
import vo.promotion.PromotionVO;

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
