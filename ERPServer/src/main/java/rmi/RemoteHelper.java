package main.java.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RemoteHelper {
    public RemoteHelper() throws RemoteException {
        LocateRegistry.createRegistry(7200);
        init();
    }

    private void init() throws RemoteException {
        new AccountRemote();
        new ClientRemote();
        new FinanceRemote();
        new GoodsRemote();
        new GoodsSortRemote();
        new InitialRemote();
        new InventoryRemote();
        new LogRemote();
        new MessageRemote();
        new PromotionRemote();
        new PurchaseRemote();
        new SaleRemote();
        new UserRemote();
    }
}
