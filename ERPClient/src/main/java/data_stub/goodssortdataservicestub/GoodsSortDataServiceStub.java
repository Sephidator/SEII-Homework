package main.java.data_stub.goodssortdataservicestub;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GoodsSortDataServiceStub implements GoodsSortDataService {
    @Override
    public GoodsSortPO find(String goodsSortID) throws RemoteException {
        return null;
    }

    @Override
    public GoodsSortPO getRoot() throws RemoteException {
        return null;
    }

    @Override
    public String insert(GoodsSortPO po) throws RemoteException {
        return null;
    }

    @Override
    public void delete(String GoodsSortID) throws RemoteException {

    }

    @Override
    public void update(GoodsSortPO po) throws RemoteException {

    }
//    @Override
//    public GoodsSortPO find(String goodsSortID) {
//        return new GoodsSortPO();
//    }
//
//    @Override
//    public ArrayList<GoodsSortPO> finds(GoodsSortQueryPO query) throws RemoteException {
//        ArrayList<GoodsSortPO> list = new ArrayList<>();
//        list.add(new GoodsSortPO());
//        return list;
//    }
//
//    @Override
//    public String insert(GoodsSortPO po) {
//        return "00000001";
//    }
//
//    @Override
//    public void delete(String GoodsSortID) {
//
//    }
//
//    @Override
//    public void update(GoodsSortPO po) {
//
//    }
//

}
