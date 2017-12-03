package main.java.server_dataservicestub.promotiondataservicestub;

import main.java.dataservice.promotiondataservice.PromotionDataService;
import main.java.po.promotion.*;

import java.rmi.RemoteException;
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
    public String insert(PromotionPO po) throws RemoteException {
        return "00000001";
    }

    @Override
    public void delete(String PromotionID) throws RemoteException {

    }

    @Override
    public void update(PromotionPO po) throws RemoteException {

    }
}
