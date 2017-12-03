package main.java.server_dataservicestub.goodsdataservicestub;

import main.java.dataservice.goodsdataservice.GoodsDataService;
import main.java.po.goods.GoodsPO;
import main.java.po.goods.GoodsQueryPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class GoodsDataServiceStub implements GoodsDataService {
    @Override
    public ArrayList<GoodsPO> find(GoodsQueryPO query) {
        ArrayList<GoodsPO> list = new ArrayList<>();
        list.add(new GoodsPO());
        return list;
    }

    @Override
    public String insert(GoodsPO po) throws RemoteException {
        return "00000001";
    }

    @Override
    public void delete(String goodsID) throws RemoteException {

    }

    @Override
    public void update(GoodsPO po) throws RemoteException {

    }

}
