package server_dataservicestub.goodssortdataservicestub;

import dataservice.goodssortdataservice.GoodsSortInfoDataService;
import po.GoodsSortPO;

import java.rmi.RemoteException;

public class GoodsSortInfoDataServiceStub implements GoodsSortInfoDataService {

    GoodsSortPO goodsSortPO=new GoodsSortPO("000000001","分类A","000000001",null,null,"无");


    @Override
    public GoodsSortPO show(GoodsSortPO po) throws RemoteException {
        return goodsSortPO;
    }
}
