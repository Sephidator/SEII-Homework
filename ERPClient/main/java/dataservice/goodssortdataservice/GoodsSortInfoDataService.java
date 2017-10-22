package dataservice.goodssortdataservice;

import po.GoodsSortPO;
import java.rmi.RemoteException;

public interface GoodsSortInfoDataService {

    public GoodsSortPO show(GoodsSortPO po) throws RemoteException;
}
