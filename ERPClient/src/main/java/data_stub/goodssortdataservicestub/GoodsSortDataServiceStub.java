package main.java.data_stub.goodssortdataservicestub;

import main.java.dataservice.goodssortdataservice.GoodsSortDataService;
import main.java.po.goods.GoodsSortPO;
import main.java.po.goods.GoodsSortQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GoodsSortDataServiceStub implements GoodsSortDataService {
    @Override
    public GoodsSortPO find(String goodsSortID) {
        return new GoodsSortPO();
    }

    @Override
    public ArrayList<GoodsSortPO> finds(GoodsSortQueryPO query) throws RemoteException {
        ArrayList<GoodsSortPO> list = new ArrayList<>();
        list.add(new GoodsSortPO());
        return list;
    }

    @Override
    public String insert(GoodsSortPO po) {
        return "00000001";
    }

    @Override
    public void delete(String GoodsSortID) {

    }

    @Override
    public void update(GoodsSortPO po) {

    }


}
