package main.java.server_dataservicestub.promotiondataservicestub;

import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.*;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PromotionDataServiceStub implements PromotionDataService {
    @Override
    public PromotionPO find(String promotionID) {
        return new PromotionPO();
    }

    @Override
    public ArrayList<PromotionPO> finds(PromotionQueryPO query) {
        ArrayList<PromotionPO> list = new ArrayList<>();
        list.add(new PromotionClientPO());
        list.add(new PromotionTotalPO());
        list.add(new PromotionGoodsPO());
        return list;
    }

    @Override
    public String insert(PromotionPO po) {
        return "00000001";
    }

    @Override
    public void delete(String PromotionID) {

    }

    @Override
    public void update(PromotionPO po) {

    }
}
