package main.java.rmi;

import main.java.data.promotiondata.PromotionData;
import main.java.dataservice.promotiondataservice.PromotionDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PromotionRemote extends UnicastRemoteObject {
    private PromotionDataService promotionDataService;

    public PromotionRemote() throws RemoteException {
        try {
            promotionDataService = new PromotionData();
            Naming.rebind("rmi://127.0.0.1:7200/PromotionDataService", promotionDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
