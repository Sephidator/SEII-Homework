package main.java.server_dataservicestub.promotiondataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.PromotionPO;
import main.java.po.promotion.PromotionQueryPO;

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
