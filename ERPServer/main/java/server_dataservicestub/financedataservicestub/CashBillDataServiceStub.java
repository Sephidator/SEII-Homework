package stub_driver.Server.main.java;

import businesslogic.blutility.ResultMessage;
import dataservice.financedataservice.CashBillDataService;
import po.CashBillPO;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by wangn on 2017.10.21.
 */
public class CashBillDataServiceStub implements CashBillDataService{

    @Override
    public ResultMessage submitDoc(CashBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage saveDoc(CashBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public CashBillPO find(String docID) throws RemoteException {
        HashMap map = new HashMap<String, Double>();
        map.put("lampX",10000);
        CashBillPO cashBillPO = new CashBillPO("XJFYD-2017-10-22-12345",0,new Date(),10000,"finance","",map);
        return cashBillPO;
    }

    @Override
    public ResultMessage insert(CashBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(CashBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage init() throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage finish() throws RemoteException {
        return ResultMessage.success;
    }
}
