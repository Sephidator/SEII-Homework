package dataservice.goodssortdataservice;

import po.GoodsSortPO;
import data.datautility.ResultMessage;
import java.rmi.RemoteException;

public interface GoodsSortInfoDataService {

    public GoodsSortPO show(GoodsSortPO po) throws RemoteException;
}
