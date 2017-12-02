package main.java.dataservice.promotiondataservice;

import main.java.po.promotion.PromotionPO;
import main.java.po.promotion.PromotionQueryPO;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionDataService extends Remote {
    ArrayList<PromotionPO> find(PromotionQueryPO query) throws RemoteException;

    String insert(PromotionPO po) throws RemoteException;

    void delete(String PromotionID) throws RemoteException;

    void update(PromotionPO po) throws RemoteException;
}
