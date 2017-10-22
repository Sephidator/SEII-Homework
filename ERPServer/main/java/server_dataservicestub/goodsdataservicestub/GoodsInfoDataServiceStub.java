package server_dataservicestub.goodsdataservicestub;

import dataservice.goodsdataservice.GoodsInfoDataService;
import data.datautility.ResultMessage;
import po.GoodsPO;

import java.rmi.RemoteException;

public class GoodsInfoDataServiceStub implements GoodsInfoDataService {

    GoodsPO goodsPO=new GoodsPO("000000001","商品A","000000001","型号A",100,10.0,14.0,
            11.0,15.0,10,"无");
    @Override
    public GoodsPO show(GoodsPO po) throws RemoteException {
        return goodsPO;
    }
}
