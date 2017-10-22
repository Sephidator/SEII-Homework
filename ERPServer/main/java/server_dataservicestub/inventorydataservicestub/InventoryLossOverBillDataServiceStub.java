package server_dataservicestub.inventorydataservicestub;

import data.datautility.ResultMessage;
import dataservice.inventorydataservice.InventoryLossOverBillDataService;
import po.GoodsPO;
import po.InventoryLossOverBillPO;


import java.rmi.RemoteException;

public class InventoryLossOverBillDataServiceStub implements InventoryLossOverBillDataService {

    InventoryLossOverBillPO inventoryOverLossBillPO=new InventoryLossOverBillPO("YSD-20171022-00002",0,
            null,"000000002",null);
    GoodsPO goodsPO=new GoodsPO("000000001","商品A","000000001","型号A",100,10.0,14.0,
            11.0,15.0,10,"无");


    @Override
    public GoodsPO findGoods(String ID, String name) throws RemoteException {
        return goodsPO;
    }

    @Override
    public ResultMessage insert(InventoryLossOverBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public int getState(String ID) throws RemoteException {
        return 0;
    }

    @Override
    public ResultMessage update(InventoryLossOverBillPO po) throws RemoteException {
        return ResultMessage.success;
    }
}
