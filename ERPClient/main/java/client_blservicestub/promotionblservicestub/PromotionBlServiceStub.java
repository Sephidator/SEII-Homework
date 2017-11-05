package client_blservicestub.promotionblservicestub;

import businesslogic.blutility.ResultMessage;
import businesslogicservice.promotionblservice.PromotionBlService;
import vo.promotion.PromotionGoodsVO;
import vo.promotion.PromotionTotalVO;
import vo.promotion.PromotionVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class PromotionBlServiceStub implements PromotionBlService {
    ArrayList<PromotionVO> vos = new ArrayList<>();
    PromotionTotalVO vo1 = new PromotionTotalVO("001", 2, new Date(), new Date(), 1000, 100, new HashMap<>());
    PromotionGoodsVO vo2 = new PromotionGoodsVO("002", 1, new Date(), new Date(), new HashMap<>(), 300);

    @Override
    public ArrayList<PromotionVO> show() {
        vos.clear();
        vos.add(vo1);
        vos.add(vo2);
        return vos;
    }

    @Override
    public ResultMessage add(PromotionVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PromotionVO vo) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(PromotionVO vo) {
        return ResultMessage.success;
    }
}
