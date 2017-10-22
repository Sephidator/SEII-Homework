package server_dataservicestub.goodsdataservicestub;

import dataservice.goodsdataservice.GoodsDataService;
import po.GoodsPO;
import data.datautility.ResultMessage;
import java.rmi.RemoteException;

public class GoodsDataServiceStub implements GoodsDataService {

    GoodsPO goodsPO=new GoodsPO("000000001","商品A","000000001","型号A",100,10.0,14.0,
            11.0,15.0,10,"无");

    @Override
    public GoodsPO findGoods(String ID, String name) throws RemoteException {
        return goodsPO;
    }

    @Override
    public ResultMessage insert(GoodsPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(GoodsPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(GoodsPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public GoodsPO detail(GoodsPO po) throws RemoteException {
        return goodsPO;
    }
}
