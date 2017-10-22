package server_dataservicestub.goodssortdataservicestub;

import data.datautility.ResultMessage;
import dataservice.goodssortdataservice.GoodsSortDataService;
import data.datautility.ResultMessage;
import po.GoodsSortPO;

import java.rmi.RemoteException;

public class GoodsSortDataServiceStub implements GoodsSortDataService {

    GoodsSortPO goodsSortPO=new GoodsSortPO("000000001","分类A","000000001",null,null,"无");

    @Override
    public GoodsSortPO findGoodsSort(String ID) throws RemoteException {
        return goodsSortPO;
    }

    @Override
    public ResultMessage insert(GoodsSortPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage delete(GoodsSortPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(GoodsSortPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public GoodsSortPO detail(GoodsSortPO po) throws RemoteException {
        return goodsSortPO;
    }
}
