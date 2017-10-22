package dataservice.goodsdataservice;

import po.GoodsPO;
import java.rmi.RemoteException;

public interface GoodsInfoDataService {

    public GoodsPO show(GoodsPO po) throws RemoteException;
}
