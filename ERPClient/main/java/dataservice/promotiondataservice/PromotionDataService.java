package dataservice.promotiondataservice;

import businesslogic.blutility.ResultMessage;
import po.PromotionPO;
import po.UserPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public interface PromotionDataService {
    ArrayList<PromotionPO> finds() throws RemoteException;

    PromotionPO find(String id) throws RemoteException;

    ResultMessage insert(PromotionPO po) throws RemoteException;

    ResultMessage update(PromotionPO po) throws RemoteException;

    ResultMessage delete(PromotionPO po) throws RemoteException;
}
