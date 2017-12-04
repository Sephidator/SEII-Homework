package main.java.data.promotiondata;

import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.PromotionPO;
import main.java.po.promotion.PromotionQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PromotionData implements PromotionDataService {
    @Override
    public ArrayList<PromotionPO> find(PromotionQueryPO query) throws RemoteException {
        return null;
    }

    @Override
    public String insert(PromotionPO po) throws RemoteException {
        return null;
    }

    @Override
    public void delete(String PromotionID) throws RemoteException {

    }

    @Override
    public void update(PromotionPO po) throws RemoteException {

    }
}
