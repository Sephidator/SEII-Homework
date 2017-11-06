package server_dataservicestub.promotiondataservicestub;

import data.datautility.ResultMessage;
import dataservice.promotiondataservice.PromotionDataService;
import po.promotion.PromotionPO;
import po.promotion.PromotionQueryPO;

import java.util.ArrayList;

public class PromotionDataServiceStub implements PromotionDataService {
    @Override
    public ArrayList<PromotionPO> find(PromotionQueryPO query) {
        ArrayList<PromotionPO> list = new ArrayList<>();
        list.add(new PromotionPO());
        return list;
    }

    @Override
    public ResultMessage insert(PromotionPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(PromotionPO po) {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PromotionPO po) {
        return ResultMessage.success;
    }

    @Override
    public String getPromotionID() {
        return "12345";
    }
}
