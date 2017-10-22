package server_dataservicestub.purchasedataservicestub;

import data.datautility.ResultMessage;
import dataservice.purchasedataservice.PurchaseTradeBillDataService;
import po.PurchaseTradeBillPO;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class PurchaseTradeBillDataServiceStub implements PurchaseTradeBillDataService{

    @Override
    public ResultMessage insert(PurchaseTradeBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public PurchaseTradeBillPO find(String ID) throws RemoteException {
        PurchaseTradeBillPO bill=new PurchaseTradeBillPO("123", "123", "123", "123",
                null, 0, "", 0, null);
        return bill;
    }

    @Override
    public ResultMessage delete(PurchaseTradeBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public ResultMessage update(PurchaseTradeBillPO po) throws RemoteException {
        return ResultMessage.success;
    }

    @Override
    public String getID() throws RemoteException {
        return "123";
    }

    @Override
    public ArrayList<PurchaseTradeBillPO> getList() throws RemoteException {
        PurchaseTradeBillPO bill1=new PurchaseTradeBillPO("123", "123", "123", "123",
                null, 0, "", 0, null);
        PurchaseTradeBillPO bill2=new PurchaseTradeBillPO("123", "123", "123", "123",
                null, 0, "", 0, null);

        ArrayList<PurchaseTradeBillPO> billList=new ArrayList<>();
        billList.add(bill1);
        billList.add(bill2);

        return billList;
    }
}
