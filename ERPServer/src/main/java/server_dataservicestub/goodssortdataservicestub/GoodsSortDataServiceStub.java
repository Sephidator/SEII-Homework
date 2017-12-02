package main.java.server_dataservicestub.goodssortdataservicestub;

import main.java.data.datautility.ResultMessage;
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
    public ResultMessage insert(GoodsSortPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage delete(GoodsSortPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage update(GoodsSortPO po) {
        return ResultMessage.SUCCESS;
    }

    @Override
    public String getGoodsSortID() {
        return "GoodsSort00000004";
    }
}
