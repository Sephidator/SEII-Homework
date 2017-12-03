package main.java.server_dataservicestub.goodssortdataservicestub;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GoodsSortDataServiceStub implements GoodsSortDataService {
    @Override
    public ArrayList<GoodsSortPO> find(GoodsSortQueryPO query) throws RemoteException {
        ArrayList<GoodsSortPO> list = new ArrayList<>();
        list.add(new GoodsSortPO());
        return list;
    }

    @Override
    public String insert(GoodsSortPO po) throws RemoteException {
        return "00000001";
    }

    @Override
    public void delete(String GoodsSortID) throws RemoteException {

    }

    @Override
    public void update(GoodsSortPO po) throws RemoteException {

    }


}
