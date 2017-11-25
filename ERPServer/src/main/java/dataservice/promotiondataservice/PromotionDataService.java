package main.java.dataservice.promotiondataservice;

import main.java.data.datautility.ResultMessage;
import main.java.po.promotion.PromotionPO;
import main.java.po.promotion.PromotionQueryPO;

import java.util.ArrayList;

public interface PromotionDataService {
    public ArrayList<PromotionPO> find(PromotionQueryPO query);

    public ResultMessage insert(PromotionPO po);

    public ResultMessage delete(PromotionPO po);

    public ResultMessage update(PromotionPO po);

    public String getPromotionID();
}
