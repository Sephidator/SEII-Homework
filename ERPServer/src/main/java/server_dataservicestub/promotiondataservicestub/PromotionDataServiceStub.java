package main.java.server_dataservicestub.promotiondataservicestub;

import main.java.data.datautility.ResultMessage;
import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.*;

import java.util.ArrayList;

public class PromotionDataServiceStub implements PromotionDataService {
    @Override
    public ArrayList<PromotionPO> find(PromotionQueryPO query) {
        ArrayList<PromotionPO> list = new ArrayList<>();
        list.add(new PromotionClientPO());
        list.add(new PromotionTotalPO());
        list.add(new PromotionGoodsPO());
        return list;
    }

    @Override
    public ResultMessage insert(PromotionPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(PromotionPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(PromotionPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getPromotionID() {
        return "Promotion00000004";
    }
}
