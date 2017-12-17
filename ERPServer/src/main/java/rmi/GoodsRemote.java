package main.java.rmi;

import main.java.data.goodsdata.GoodsData;
import main.java.dataservice.goodsdataservice.GoodsDataService;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GoodsRemote extends UnicastRemoteObject {
    private GoodsDataService goodsDataService;

    protected GoodsRemote() throws RemoteException {
        try {
            goodsDataService = new GoodsData();
            Naming.rebind("rmi://127.0.0.1:7200/GoodsDataService", goodsDataService);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
