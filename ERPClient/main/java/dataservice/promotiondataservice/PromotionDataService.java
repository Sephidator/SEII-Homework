package dataservice.promotiondataservice;

import businesslogic.blutility.ResultMessage;
import po.promotion.PromotionPO;
import po.promotion.PromotionQueryPO;

import java.util.ArrayList;

public interface PromotionDataService {
    public ArrayList<PromotionPO> find(PromotionQueryPO query);

    public ResultMessage insert(PromotionPO po);

    public ResultMessage delete(PromotionPO po);

    public ResultMessage update(PromotionPO po);

    public String getPromotionID();
}
